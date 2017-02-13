package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job;

import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.config.BatchConfiguration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Pawel on 2017-02-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BatchConfiguration.class})
public class JobLauncherTest {

    private static final LocalDate DATE = LocalDate.of(2016, 1, 5);
    private long id = 326;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private Job importSnapshotsJob;


    @Test
    public void testJob() throws Exception {

        //JobExecution execution = new JobExecution(new JobInstance(1L, "name"),1L,  new JobParameters(ImmutableMap.of("currentDate", new JobParameter(Date.valueOf(DATE), false), "id", new JobParameter(new Random().nextLong(), true))), "config");

        //importSnapshotsJob.execute(execution);
        IntStream.range(0, 10).parallel().forEach(i -> {
            try {
                JobExecution execution = jobLauncherTestUtils.launchJob(new JobParameters(ImmutableMap.of("currentDate", new JobParameter(Date.valueOf(DATE.plusDays(i)), false), "id", new JobParameter(new Random().nextLong(), true))));
                Assert.assertEquals("COMPLETED", execution.getExitStatus().getExitCode());
            } catch (Exception e) {
                e.printStackTrace();
            }});

    }
}