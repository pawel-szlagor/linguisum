package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.config;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.common.collect.ImmutableMap;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.processor.IntegratorProcessor;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.reader.IntegratorReader;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.writer.IntegratorWriter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.config.BasicSemanticConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.tasklet.HolonCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.SnapshotSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.BasicMySQLConfig;

/**
 * Created by Pawel on 2017-02-08.
 */
@Configuration
@ComponentScan("pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.*")
@EnableBatchProcessing
@EnableScheduling
@Import({ BasicMySQLConfig.class, BasicMongoConfig.class, BasicSemanticConfig.class })
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IntegratorProcessor processor;

    @Autowired
    private IntegratorReader reader;

    @Autowired
    private IntegratorWriter bulkWriter;

    @Autowired
    @Qualifier(value = "importJob")
    private Job importSnapshotsJob;

    @Autowired
    private JobRepository jobRepository;

    // tag::readerwriterprocessor[]

    @Bean
    public ItemWriter<Snapshot> writer() {
        MongoItemWriter<Snapshot> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("iHouse");
        return writer;
    }

    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importJob(JobCompletionNotificationListener listener, StepExecutionListener stepExecutionListener) {
        return jobBuilderFactory.get("importJob").incrementer(new RunIdIncrementer()).listener(listener).flow(importSnapshots(stepExecutionListener)).end().build();
    }

    @Bean
    public Step importSnapshots(StepExecutionListener stepExecutionListener) {
        return stepBuilderFactory.get("importSnapshots").listener(stepExecutionListener).<SnapshotSourceDto, Snapshot>chunk(10000).reader(reader).processor(processor).writer(bulkWriter).throttleLimit(10).build();
    }
    // end::jobstep[]

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier(value = "sourceDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

    @Bean
    public JobRepository jobRepository(@Qualifier(value = "sourceDataSource") DataSource dataSource,
            @Qualifier(value = "sourceTransactionManager") PlatformTransactionManager transactionManager) throws Exception {
        final JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.getProductName());
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public JobLauncher jobLauncher() {
        final SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }

    //@Scheduled(cron = "0 0 0 * * ?")
    public void importSnapchotsTask() throws Exception {

        final LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Job Started at :" + dateTime);
        final JobParameters params = new JobParameters(ImmutableMap.of("currentDate", new JobParameter(Date.valueOf(dateTime.toLocalDate().minusDays(1)), false), "id", new JobParameter(new Random().nextLong(), true)));
        JobExecution execution = jobLauncher().run(importSnapshotsJob, params);

        System.out.println("Job finished with status :" + execution.getStatus());
    }

    @Bean
    public HolonCache holonCache() {
        return new HolonCache();
    }
}
