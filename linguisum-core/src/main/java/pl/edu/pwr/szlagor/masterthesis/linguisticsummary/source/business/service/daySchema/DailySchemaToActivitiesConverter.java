package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema;

import java.time.LocalDate;
import java.util.List;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySchemaSourceDto;

/**
 * Created by Pawel on 2017-02-02.
 */
public interface DailySchemaToActivitiesConverter {
    List<ActivitySourceDto> convert(DaySchemaSourceDto daily, LocalDate date);
}
