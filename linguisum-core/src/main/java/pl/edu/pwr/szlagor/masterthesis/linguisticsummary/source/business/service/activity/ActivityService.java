package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity;

import java.util.Collection;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySourceDto;

/**
 * Created by Pawel on 2017-02-02.
 */
public interface ActivityService {

    void save(ActivitySourceDto activitySourceDto);

    void save(Collection<ActivitySourceDto> activitySourceDtos);

}
