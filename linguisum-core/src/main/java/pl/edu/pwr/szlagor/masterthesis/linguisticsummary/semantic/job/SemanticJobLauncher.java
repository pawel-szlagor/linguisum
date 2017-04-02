package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.config.SemanticBatchConfiguration;

/**
 * Created by Pawel on 2017-02-08.
 */
@Component
public class SemanticJobLauncher {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SemanticBatchConfiguration.class);
    }
}
