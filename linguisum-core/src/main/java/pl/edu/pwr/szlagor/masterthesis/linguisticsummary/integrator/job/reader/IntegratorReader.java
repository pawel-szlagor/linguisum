package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.reader;

import java.util.Date;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.day.DaySourceService;

/**
 * Created by Pawel on 2017-02-09.
 */
@StepScope
@Component
public class IntegratorReader implements ItemReader<DaySourceDto> {

    @Value("#{jobParameters['currentDate']}")
    private Date currentDate;

    private final DaySourceService daySourceService;

    @Autowired
    public IntegratorReader(DaySourceService daySourceService) {
        this.daySourceService = daySourceService;
    }

    @Override
    public DaySourceDto read() throws Exception {
        return daySourceService.findDaySourceByDate(new java.sql.Date(new java.util.Date().getTime()).toLocalDate());
    }
}
