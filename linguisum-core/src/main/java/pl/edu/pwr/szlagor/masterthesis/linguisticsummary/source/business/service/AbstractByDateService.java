package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Created by Pawel on 2017-01-29.
 */
public abstract class AbstractByDateService<DTO extends ObservationTimeAware, E extends ObservationTimeAware, ID extends Serializable> extends AbstractService<DTO, E, ID> implements CommonByDateService<DTO, ID> {

    private Map<Integer, List<DTO>> orderedByDate;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @SuppressWarnings(value = "unchecked")
    @Transactional(readOnly = true)
    @Override
    public synchronized List<DTO> findByDate(LocalDate date) {
        System.out.println(format("Finding list of %s in day %s", getEntityClass().getSimpleName(), date.toString()));
        Session session = entityManagerFactoryBean.getNativeEntityManagerFactory().unwrap(SessionFactory.class).openSession();
        final Query query = session.createQuery("from " + getEntityClass().getSimpleName() + " where DATE(observationTime) = :date");
        query.setParameter("date", java.sql.Date.valueOf(date));
        final List<DTO> list = query.<DTO>list();
        session.close();
        return getMapperFacade().mapAsList(list, getDtoClass());
    }

    @Override
    public synchronized List<DTO> findByDateAndHour(LocalDateTime date) {
        System.out.println(format("Finding list of %s in day %s", getEntityClass().getSimpleName(), date.toString()));
        /*StatelessSession session = entityManagerFactoryBean.getNativeEntityManagerFactory().unwrap(SessionFactory.class).openStatelessSession();
        final Query query = session.createQuery("from " + getEntityClass().getSimpleName() + " as entity where DATE(entity.observationTime) = :date AND HOUR(entity.observationTime) = :hour");
        query.setParameter("date", java.sql.Date.valueOf(date.toLocalDate()));
        query.setParameter("hour", date.getHour());
        final List<DTO> list = query.<DTO>list();
        session.close();*/
        if(orderedByDate == null){
            orderedByDate = findByDate(date.toLocalDate()).stream().collect(Collectors.groupingBy(l->l.getObservationTime().getHour()));
        }
        return Optional.ofNullable(orderedByDate.get(date.getHour())).orElse(Collections.emptyList());
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
