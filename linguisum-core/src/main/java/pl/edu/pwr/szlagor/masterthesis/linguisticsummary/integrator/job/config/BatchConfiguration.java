package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DayDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.processor.IntegratorProcessor;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.reader.IntegratorReader;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.BasicMySQLConfig;

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
    private IntegratorProcessor processor;

    @Autowired
    private IntegratorReader reader;

    // tag::readerwriterprocessor[]

    @Bean
    public JdbcBatchItemWriter<DayDto> writer(DataSource dataSource) {
        JdbcBatchItemWriter<DayDto> writer = new JdbcBatchItemWriter<DayDto>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<DayDto>());
        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setDataSource(dataSource);
        return writer;
    }

    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importDataJob(JobCompletionNotificationListener listener, DataSource datasource) {
        return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1(datasource)).end().build();
    }

    @Bean
    public Step step1(DataSource datasource) {
        return stepBuilderFactory.get("step1").<DaySourceDto, DayDto>chunk(10).reader(reader).processor(processor).writer(writer(datasource)).build();
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
}
