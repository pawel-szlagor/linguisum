package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import static java.lang.String.format;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;

/**
 * Created by Pawel on 2017-01-29.
 */
public abstract class AbstractByDateService<DTO, E, ID extends Serializable> extends AbstractService<DTO, E, ID> implements CommonByDateService<DTO, ID> {


    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @SuppressWarnings(value = "unchecked")
    @Transactional(readOnly = true)
    @Override
    public synchronized List<DTO> findByDate(LocalDate date) {
        System.out.println(format("Finding All list of %s. Current Thread: %d", getEntityClass().getSimpleName(), Thread.currentThread().getId()));
        Session session = entityManagerFactoryBean.getNativeEntityManagerFactory().unwrap(SessionFactory.class).openSession();
        final Query query = session.createQuery("from " + getEntityClass().getSimpleName() + " where DATE(observationTime) = :date");
        query.setParameter("date", java.sql.Date.valueOf(date));
        final List<DTO> list = query.<DTO>list();
        session.close();
        return getMapperFacade().mapAsList(list, getDtoClass());
    }

    protected MapperFacade getMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(getDtoClass(), getEntityClass()).byDefault().register();
        factory.classMap(getEntityClass(), getDtoClass()).byDefault().register();
        return factory.getMapperFacade();
    }

    protected abstract JpaRepository<E, ID> getRepository();

    protected abstract Class<E> getEntityClass();

    protected abstract Class<DTO> getDtoClass();

}
