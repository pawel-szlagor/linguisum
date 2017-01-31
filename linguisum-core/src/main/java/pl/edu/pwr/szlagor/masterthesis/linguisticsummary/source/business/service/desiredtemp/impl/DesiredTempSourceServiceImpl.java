package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.desiredtemp.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DesiredTempSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.desiredtemp.DesiredTempSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.DesiredTempSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.DesiredTempSourceRepository;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Service
public class DesiredTempSourceServiceImpl extends AbstractService<DesiredTempSourceDto, DesiredTempSource, Long>
        implements DesiredTempSourceService {

    private final DesiredTempSourceRepository repository;

    @Autowired
    public DesiredTempSourceServiceImpl(DesiredTempSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Class<DesiredTempSource> getEntityClass() {
        return DesiredTempSource.class;
    }

    @Override
    protected Class<DesiredTempSourceDto> getDtoClass() {
        return DesiredTempSourceDto.class;
    }
}
