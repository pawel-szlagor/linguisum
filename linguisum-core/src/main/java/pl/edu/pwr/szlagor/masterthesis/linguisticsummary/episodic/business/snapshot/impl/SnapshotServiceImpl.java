package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.business.snapshot.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.business.snapshot.SnapshotService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.SnapshotDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;

/**
 * Created by Pawel on 2017-03-07.
 */
@Getter
@Service
public class SnapshotServiceImpl implements SnapshotService {

    private final SnapshotRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public SnapshotServiceImpl(SnapshotRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Transactional(readOnly = true)
    @Override
    public synchronized SnapshotDto findByLocalDateTime(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime);
        Snapshot entity = getRepository().findByDateAndTime(localDateTime.toLocalDate(), localDateTime.toLocalTime());
        return getMapperFacade().map(entity, SnapshotDto.class);
    }

    @SuppressWarnings(value = "unchecked")
    @Transactional(readOnly = true)
    @Override
    public synchronized List<SnapshotDto> findAll() {
        return getMapperFacade().mapAsList(repository.findAll(), SnapshotDto.class);
    }

    protected MapperFacade getMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(SnapshotDto.class, Snapshot.class).byDefault().register();
        factory.classMap(Snapshot.class, SnapshotDto.class).byDefault().register();
        return factory.getMapperFacade();
    }
}
