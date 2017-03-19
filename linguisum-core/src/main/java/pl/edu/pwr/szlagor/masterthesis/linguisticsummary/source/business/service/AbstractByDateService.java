package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Sets;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.ObservationTimeAwareRepository;

/**
 * Created by Pawel on 2017-01-29.
 */
public abstract class AbstractByDateService<DTO extends ObservationTimeAware, E extends ObservationTimeAware, ID extends Serializable> extends AbstractService<DTO, E, ID> implements CommonByDateService<DTO, ID> {

    private Map<Integer, List<DTO>> orderedByDate;

    @Autowired
    @PersistenceContext(unitName = "mySQL")
    private EntityManager entityManagerFactoryBean;

    @SuppressWarnings(value = "unchecked")
    @Transactional(readOnly = true)
    @Override
    public synchronized List<DTO> findByDate(LocalDate date) {
        Session session = entityManagerFactoryBean.unwrap(SessionFactory.class).openSession();
        final Query query = session.createQuery("from " + getEntityClass().getSimpleName() + " where DATE(OBSERVATION_TIME) = :date");
        query.setParameter("date", java.sql.Date.valueOf(date));
        final List<DTO> list = query.<DTO>list();
        session.close();
        return getMapperFacade().mapAsList(list, getDtoClass());
    }

    @Override
    public synchronized List<DTO> findByDateAndHour(LocalDateTime date) {
        /*StatelessSession session = entityManagerFactoryBean.getNativeEntityManagerFactory().unwrap(SessionFactory.class).openStatelessSession();
        final Query query = session.createQuery("from " + getEntityClass().getSimpleName() + " as entity where DATE(entity.observationTime) = :date AND HOUR(entity.observationTime) = :hour");
        query.setParameter("date", java.sql.Date.valueOf(date.toLocalDate()));
        query.setParameter("hour", date.getHour());
        final List<DTO> list = query.<DTO>list();
        session.close();*/
        if (orderedByDate == null) {
            orderedByDate = findByDate(date.toLocalDate()).stream().collect(Collectors.groupingBy(l -> l.getObservationTime().getHour()));
        }
        return Optional.ofNullable(orderedByDate.get(date.getHour())).orElse(Collections.emptyList());
    }

    @Override
    public List<DTO> findByObservationTime(LocalDateTime dateTime) {
        return getMapperFacade().mapAsList(getRepository().findByObservationTime(dateTime), getDtoClass());
    }

    @Override
    public Set<DTO> findByObservationTimeAround(LocalDateTime dateTime) {
        Assert.isTrue(dateTime.getSecond() % 5 == 0);
        Set<DTO> dtos = Sets.newHashSet();
        dtos.addAll(findByObservationTime(dateTime.minusSeconds(2)));
        dtos.addAll(findByObservationTime(dateTime.minusSeconds(1)));
        dtos.addAll(findByObservationTime(dateTime));
        dtos.addAll(findByObservationTime(dateTime.plusSeconds(1)));
        dtos.addAll(findByObservationTime(dateTime.plusSeconds(2)));
        return dtos;
    }

    protected MapperFacade getMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(getDtoClass(), getEntityClass()).byDefault().register();
        factory.classMap(getEntityClass(), getDtoClass()).byDefault().register();
        return factory.getMapperFacade();
    }

    protected abstract ObservationTimeAwareRepository<E, ID> getRepository();

    protected abstract Class<E> getEntityClass();

    protected abstract Class<DTO> getDtoClass();

}
