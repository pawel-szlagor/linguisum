package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
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
    private List<DeviceSourceDto> cachedDevices;

    @Autowired
    public DeviceSourceServiceImpl(DeviceSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DeviceSourceDto> findAll() {
        if (cachedDevices == null) {
            cachedDevices = findAllInBulk();
        }
        return cachedDevices;
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
