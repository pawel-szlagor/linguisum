package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.ImmutableMap;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.config.BatchConfiguration;

/**
 * Created by Pawel on 2017-02-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BatchConfiguration.class})
public class JobLauncherTest {

    private static final LocalDate DATE = LocalDate.of(2016, 1, 5);
    @Autowired
    private Job importDataJob;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testJob() throws Exception {

        final JobExecution jobExecution = new JobExecution(new JobInstance(3L, "importDataJob"), 1L, new JobParameters(ImmutableMap.of("currentDate", new JobParameter(Date.valueOf(DATE), true))), "BatchConfiguration");
        //final JobExecution jobExecution = new JobExecution(1L);
        final JobExecution execution = jobLauncherTestUtils.launchJob(new JobParameters(ImmutableMap.of("currentDate", new JobParameter(Date.valueOf(DATE), true))));

        Assert.assertEquals("COMPLETED", execution.getExitStatus());
    }
}