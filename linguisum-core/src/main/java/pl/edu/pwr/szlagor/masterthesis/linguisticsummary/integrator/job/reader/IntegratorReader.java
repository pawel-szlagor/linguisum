package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.SnapshotSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.day.DaySourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.snapshot.SnapshotSourceService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Pawel on 2017-02-09.
 */
@StepScope
@Component
public class IntegratorReader implements ItemReader<SnapshotSourceDto>, InitializingBean {

    @Value("#{jobParameters['currentDate']}")
    private Date currentDate;
    @Value("#{jobParameters['id']}")
    private long idJob;
    private LocalDateTime currentDateTime;
    private List<DaySourceDto> day;
    private final DaySourceService daySourceService;
    private final SnapshotSourceService snapshotSourceService;
    private LocalDate dayToRead;
    private long counter = 0L;

    @Autowired
    public IntegratorReader(DaySourceService daySourceService, SnapshotSourceService snapshotSourceService) {
        this.daySourceService = daySourceService;
        this.snapshotSourceService = snapshotSourceService;
    }

    @Override
    public synchronized SnapshotSourceDto read() {
        try {
            if (counter < 86400) {
                SnapshotSourceDto snapshotSourceDto = snapshotSourceService.findByHour(currentDateTime.plusSeconds(counter), day);
                snapshotSourceDto.setObservationTime(currentDateTime.plusSeconds(counter));
                counter += 5;
                return snapshotSourceDto;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void afterPropertiesSet() {
        dayToRead = new java.sql.Date(currentDate.getTime()).toLocalDate();
        currentDateTime = new java.sql.Date(currentDate.getTime()).toLocalDate().atStartOfDay();
        day = daySourceService.findDaySourceByDateHourly(dayToRead);
    }
}
