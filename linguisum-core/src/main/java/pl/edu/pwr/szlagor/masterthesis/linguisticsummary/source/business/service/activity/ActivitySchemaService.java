package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySchemaSourceDto;

/**
 * Created by Pawel on 2017-02-02.
 */
public interface ActivitySchemaService {
    ActivitySchemaSourceDto randomizeDaily(ActivitySchemaSourceDto activitySchema);
}
