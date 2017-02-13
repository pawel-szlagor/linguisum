package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.day;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Pawel on 2017-02-08.
 */
public interface DaySourceService {
    DaySourceDto findDaySourceByDate(LocalDate date);

    List<DaySourceDto> findDaySourceByDateHourly(LocalDate date);
}
