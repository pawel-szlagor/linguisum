package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema;

import java.time.LocalDate;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySchemaSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;

/**
 * Created by Pawel on 2017-02-02.
 */
public interface DailyService {
    void createDaily(LocalDate date, PersonSourceDto user1);

    DaySchemaSourceDto randomizeDaily(DaySchemaSourceDto daily);

}
