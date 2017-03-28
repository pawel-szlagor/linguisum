package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.reader;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Pawel on 2017-02-09.
 */
@StepScope
@Component
public class SemanticIntegratorReader implements ItemReader<Snapshot>, InitializingBean, StepExecutionListener {

    private static final int PORTION_COUNT = 10000;
    private AtomicLong counter = new AtomicLong(0);

    private final ItemReader<Snapshot> mongoItemReader;
    private StepExecution stepExecution;

    @Autowired
    public SemanticIntegratorReader(@Qualifier(value = "pageableReader") ItemReader<Snapshot> mongoItemReader) {
        this.mongoItemReader = mongoItemReader;
    }

    @Override
    public Snapshot read() throws Exception {
        // counter.getAndIncrement();
        return readPortion();
    }

    private Snapshot readPortion() {
        try {
            return mongoItemReader.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void afterPropertiesSet() {

    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        this.stepExecution.getExecutionContext().put("readerExhausted", Boolean.FALSE);
        /*
         * mongoItemReader.setQuery(String.format("{'id':{$gt:%s}, $lte:%s}}",
         * stepExecution.getExecutionContext().get("fromId"),
         * stepExecution.getExecutionContext().get("toId")));
         */
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}
