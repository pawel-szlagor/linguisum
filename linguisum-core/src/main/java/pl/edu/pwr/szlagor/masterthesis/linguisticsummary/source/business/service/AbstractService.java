package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pawel on 2017-01-29.
 */
public abstract class AbstractService<DTO, E> implements CommonService<DTO> {

    @Transactional
    @Override
    public DTO save(DTO dto) {
        Assert.notNull(dto);
        Mapper mapper = new DozerBeanMapper();
        E entity = mapper.map(dto, getEntityClass());
        getRepository().save(entity);
        return mapper.map(entity, getDtoClass());
    }

    @Transactional
    @Override
    public List<DTO> save(Collection<DTO> dtos) {
        Assert.notNull(dtos);
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory
                ()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(getDtoClass(), getEntityClass()).byDefault().register();
        Collection<E> entities = dtos.stream().map(d -> factory.getMapperFacade().map(d, getEntityClass())).collect
                (Collectors.toList());
        getRepository().save(entities);
        return entities.stream().map(e -> factory.getMapperFacade().map(e, getDtoClass())).collect(Collectors.toList());
    }


    protected abstract JpaRepository<E, ? extends Serializable> getRepository();

    protected abstract Class<E> getEntityClass();

    protected abstract Class<DTO> getDtoClass();

}
