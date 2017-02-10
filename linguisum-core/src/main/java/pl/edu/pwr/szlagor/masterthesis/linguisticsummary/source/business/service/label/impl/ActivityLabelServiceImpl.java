package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.label.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivityLabelSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractByDateService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.label.ActivityLabelService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.ActivityLabelSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.ActivityLabelSourceRepository;

/**
 * Created by Pawel on 2017-02-05.
 */
@Getter
@Service
public class ActivityLabelServiceImpl extends AbstractByDateService<ActivityLabelSourceDto, ActivityLabelSource, Long> implements ActivityLabelService {

    private final ActivityLabelSourceRepository repository;

    @Autowired
    public ActivityLabelServiceImpl(ActivityLabelSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Class<ActivityLabelSource> getEntityClass() {
        return ActivityLabelSource.class;
    }

    @Override
    protected Class<ActivityLabelSourceDto> getDtoClass() {
        return ActivityLabelSourceDto.class;
    }
}
