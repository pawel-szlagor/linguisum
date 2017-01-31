package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.RoomSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.RoomSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.RoomSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.RoomSourceRepository;

import javax.transaction.Transactional;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Transactional
@Service
public class RoomSourceServiceImpl extends AbstractService<RoomSourceDto, RoomSource, Long> implements
        RoomSourceService {

    private final RoomSourceRepository repository;

    @Autowired
    public RoomSourceServiceImpl(RoomSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomSourceDto findByName(String name) {
        Assert.notNull(name);
        RoomSource RoomSource = getRepository().findByName(name);
        return getMapperFacade().map(RoomSource, getDtoClass());
    }

    @Override
    protected Class<RoomSource> getEntityClass() {
        return RoomSource.class;
    }

    @Override
    protected Class<RoomSourceDto> getDtoClass() {
        return RoomSourceDto.class;
    }
}
