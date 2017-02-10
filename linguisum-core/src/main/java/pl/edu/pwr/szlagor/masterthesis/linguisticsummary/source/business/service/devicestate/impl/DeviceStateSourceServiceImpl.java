package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.devicestate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceStateSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.devicestate.DeviceStateSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.DeviceStateSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.DeviceStateSourceRepository;

/**
 * Created by Pawel on 2017-02-02.
 */
@Getter
@Service
public class DeviceStateSourceServiceImpl extends AbstractService<DeviceStateSourceDto, DeviceStateSource, Long> implements DeviceStateSourceService {

    private final DeviceStateSourceRepository repository;

    @Autowired
    public DeviceStateSourceServiceImpl(DeviceStateSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Class<DeviceStateSource> getEntityClass() {
        return DeviceStateSource.class;
    }

    @Override
    protected Class<DeviceStateSourceDto> getDtoClass() {
        return DeviceStateSourceDto.class;
    }
}
