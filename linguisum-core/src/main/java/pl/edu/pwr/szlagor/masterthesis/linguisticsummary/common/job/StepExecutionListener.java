package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.common.job;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * Created by Paweł on 2017-02-12.
 */
@Component
public class StepExecutionListener extends StepListenerSupport {
    private LocalDateTime currentTime = LocalDateTime.now();
    private LocalDateTime readTime = LocalDateTime.now();
    private LocalDateTime processTime = LocalDateTime.now();
    private LocalDateTime writeTime = LocalDateTime.now();
    private Duration readDuration = Duration.ZERO;
    private Duration processDuration = Duration.ZERO;
    private Duration writeDuration = Duration.ZERO;
    private static Long counter = 0L;

    @Override
    public void beforeRead() {
        // System.out.println("before read: "+Duration.between(currentTime, LocalDateTime.now()) );
        // readTime = LocalDateTime.now();
    }

    @Override
    public void afterRead(Object item) {
        // System.out.println("after read: "+ Duration.between(currentTime, LocalDateTime.now()) );
        // readDuration = readDuration.plus(Duration.between(LocalDateTime.now(), readTime));
    }

    @Override
    public void beforeProcess(Object item) {
        // System.out.println("before process: "+ Duration.between(currentTime, LocalDateTime.now()) );
        // processTime = LocalDateTime.now();
    }

    @Override
    public void afterProcess(Object item, Object result) {
        // System.out.println("after process: "+ Duration.between(currentTime, LocalDateTime.now()) );
        // processDuration = processDuration.plus(Duration.between(LocalDateTime.now(), processTime));
    }

    @Override
    public void beforeWrite(List items) {
        // System.out.println("before write: "+ Duration.between(currentTime, LocalDateTime.now()) );
        // writeTime = LocalDateTime.now();
    }

    @Override
    public void afterWrite(List items) {
        // System.out.println("after write: "+ Duration.between(currentTime, LocalDateTime.now()) );
        // writeDuration = writeDuration.plus(Duration.between(LocalDateTime.now(), writeTime));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        /*
         * System.out.println("total time of read: " + readDuration);
         * System.out.println("total time of process: " + processDuration);
         * System.out.println("total time of write: " + writeDuration);
         */
        if (counter++ < 1000) {
            return new ExitStatus("REPEAT");
        } else {
            return new ExitStatus("FINISHED");
        }
    }

    @Override
    public void afterChunk(ChunkContext context) {
        /*
         * System.out.println("total time of read: " + readDuration);
         * System.out.println("total time of process: " + processDuration);
         * System.out.println("total time of write: " + writeDuration);
         */
        super.afterChunk(context);
    }

}
