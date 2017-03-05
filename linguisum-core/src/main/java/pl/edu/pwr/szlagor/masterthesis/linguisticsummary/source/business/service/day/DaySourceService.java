package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.day;

import java.time.LocalDate;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;

/**
 * Created by Pawel on 2017-02-08.
 */
public interface DaySourceService {
    DaySourceDto findDaySourceByDateOrderedByTime(LocalDate date);

}
