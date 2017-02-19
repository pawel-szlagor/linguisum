package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.config.BatchConfiguration;

/**
 * Created by Pawel on 2017-02-08.
 */
@Component
public class AdminConsole {


    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BatchConfiguration.class);
    }
}
