package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.snapshot;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.SnapshotSourceDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Paweł on 2017-02-11.
 */
public interface SnapshotSourceService {

    SnapshotSourceDto findByDateTime(LocalDateTime dateTime, DaySourceDto day);

    SnapshotSourceDto findByHour(LocalDateTime currentDateTime, List<DaySourceDto> day);
}
