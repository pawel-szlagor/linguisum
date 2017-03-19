package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;

/**
 * Created by Pawel on 2017-01-29.
 */
public abstract class AbstractService<DTO, E, ID extends Serializable> implements CommonService<DTO, ID> {


    @Autowired
    @Qualifier("entityManagerFactory")
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Transactional
    @Override
    public synchronized DTO save(DTO dto) {
        Assert.notNull(dto);
        E entity = getMapperFacade().map(dto, getEntityClass());
        getRepository().save(entity);
        return getMapperFacade().map(entity, getDtoClass());
    }

    @Transactional
    @Override
    public synchronized void save(Collection<DTO> dtos) {
        Assert.notNull(dtos);
        Collection<E> entities = dtos.stream().map(d -> getMapperFacade().map(d, getEntityClass())).collect(Collectors.toList());
        getRepository().save(entities);
        //return entities.stream().map(e -> getMapperFacade().map(e, getDtoClass())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public synchronized DTO findById(ID id) {
        Assert.notNull(id);
        E entity = getRepository().findOne(id);
        return getMapperFacade().map(entity, getDtoClass());
    }

    @Transactional(readOnly = true)
    @Override
    public synchronized List<DTO> findAll() {
        List<E> entities = getRepository().findAll();
        return entities.stream().map(e -> getMapperFacade().map(e, getDtoClass())).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public synchronized void saveInBulk(Collection<DTO> collection) {
        StatelessSession session = entityManagerFactoryBean.getNativeEntityManagerFactory().unwrap(SessionFactory.class).openStatelessSession();
        Transaction tx = session.beginTransaction();
        List<E> entities = getMapperFacade().mapAsList(collection, getEntityClass());
        for (E entity : entities) {
            session.insert(entity);
        }
        tx.commit();
        session.close();
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(readOnly = true)
    @Override
    public synchronized List<DTO> findAllInBulk() {
        Session session = entityManagerFactoryBean.getNativeEntityManagerFactory().unwrap(SessionFactory.class).openSession();
        final List<DTO> list = session.createQuery("from " + getEntityClass().getSimpleName()).<DTO>list();
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
