package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DayDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;

/**
 * Created by Pawel on 2017-02-08.
 */
@StepScope
@Component
public class IntegratorProcessor implements ItemProcessor<DaySourceDto, DayDto> {

    //@Autowired
    private SnapshotRepository snapshotRepository;

    @Override
    public DayDto process(DaySourceDto item) throws Exception {
        return new DayDto();
    }
}
