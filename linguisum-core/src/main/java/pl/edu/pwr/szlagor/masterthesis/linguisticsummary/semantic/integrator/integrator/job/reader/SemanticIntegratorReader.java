package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.reader;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

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
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.SemanticReadItem;

/**
 * Created by Pawel on 2017-02-09.
 */
@StepScope
@Component
public class SemanticIntegratorReader implements ItemReader<SemanticReadItem>, InitializingBean, StepExecutionListener {

    private static final int PORTION_COUNT = 10000;
    private AtomicLong counter = new AtomicLong(0);

    private final ItemReader<Snapshot> mongoItemReader;
    private StepExecution stepExecution;

    @Autowired
    public SemanticIntegratorReader(@Qualifier(value = "pageableReader") ItemReader<Snapshot> mongoItemReader) {
        this.mongoItemReader = mongoItemReader;
    }

    @Override
    public synchronized SemanticReadItem read() throws Exception {
        final List<Snapshot> snapshots = IntStream.range(0, PORTION_COUNT)
                                                  .mapToObj(i -> readPortion())
                                                  .filter(Objects::nonNull)
                                                  .collect(toList());
        counter.getAndAdd(PORTION_COUNT);
        System.out.println("Wczytano: " + counter);
        if (snapshots.isEmpty()) {
            stepExecution.getExecutionContext().put("readerExhausted", Boolean.TRUE);
            return null;
        }
        return new SemanticReadItem(snapshots);
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
