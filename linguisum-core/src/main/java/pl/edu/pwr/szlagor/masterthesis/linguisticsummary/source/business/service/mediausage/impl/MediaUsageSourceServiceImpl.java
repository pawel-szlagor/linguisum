package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.mediausage.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.MediaUsageSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractByDateService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.mediausage.MediaUsageSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.MediaUsageSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.MediaUsageSourceRepository;

/**
 * Created by Pawel on 2017-02-01.
 */
@Getter
@Service
public class MediaUsageSourceServiceImpl extends AbstractByDateService<MediaUsageSourceDto, MediaUsageSource, Long> implements MediaUsageSourceService {

    private final MediaUsageSourceRepository repository;

    @Autowired
    public MediaUsageSourceServiceImpl(MediaUsageSourceRepository repository) {
        this.repository = repository;
    }


    @Override
    protected Class<MediaUsageSource> getEntityClass() {
        return MediaUsageSource.class;
    }

    @Override
    protected Class<MediaUsageSourceDto> getDtoClass() {
        return MediaUsageSourceDto.class;
    }
}
