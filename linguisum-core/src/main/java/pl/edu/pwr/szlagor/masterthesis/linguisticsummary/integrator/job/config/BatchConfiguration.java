package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.processor.IntegratorProcessor;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.reader.IntegratorReader;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.writer.IntegratorWriter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.SnapshotSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.BasicMySQLConfig;

import javax.sql.DataSource;

/**
 * Created by Pawel on 2017-02-08.
 */
@Configuration
@ComponentScan("pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.*")
@EnableBatchProcessing
@Import({BasicMySQLConfig.class, BasicMongoConfig.class})
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
    private StepExecutionListener stepExecutionListener;

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
    public Job importSnapshotsJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importSnapshotsJob").incrementer(new RunIdIncrementer()).listener(listener).flow(importSnapshots()).end().build();
    }

    @Bean
    public Step importSnapshots() {
        return stepBuilderFactory.get("importSnapshots").listener(stepExecutionListener).<SnapshotSourceDto, Snapshot>chunk(100000).reader(reader).processor(processor).writer(bulkWriter).throttleLimit(10).build();
    }
    // end::jobstep[]

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        final JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.getProductName());
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        return new SyncTaskExecutor();
    }


}
