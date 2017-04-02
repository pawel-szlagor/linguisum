package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.job;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.config.SemanticBatchConfiguration;

/**
 * Created by Pawel on 2017-02-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SemanticBatchConfiguration.class })
public class SemanticJobLauncherTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testJob() throws Exception {

        // JobExecution execution = new JobExecution(new JobInstance(1L, "name"),1L, new
        // JobParameters(ImmutableMap.of("currentDate", new JobParameter(Date.valueOf(DATE), false), "id", new
        // JobParameter(new Random().nextLong(), true))), "config");

        // importJob.execute(execution);
        try {
            JobExecution execution = jobLauncherTestUtils.launchJob();
            Assert.assertEquals("COMPLETED", execution.getExitStatus().getExitCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
