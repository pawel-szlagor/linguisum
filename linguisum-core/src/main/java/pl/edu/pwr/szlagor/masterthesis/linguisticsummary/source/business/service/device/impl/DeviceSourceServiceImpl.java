package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.DeviceSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.DeviceSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.DeviceSourceRepository;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Service
public class DeviceSourceServiceImpl extends AbstractService<DeviceSourceDto, DeviceSource, Long> implements
        DeviceSourceService {

    private final DeviceSourceRepository repository;

    @Autowired
    public DeviceSourceServiceImpl(DeviceSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Class<DeviceSource> getEntityClass() {
        return DeviceSource.class;
    }

    @Override
    protected Class<DeviceSourceDto> getDtoClass() {
        return DeviceSourceDto.class;
    }
}
