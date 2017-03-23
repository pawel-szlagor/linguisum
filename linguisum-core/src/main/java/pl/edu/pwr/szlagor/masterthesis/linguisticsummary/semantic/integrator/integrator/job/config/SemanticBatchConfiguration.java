package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.config;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.common.collect.ImmutableMap;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.config.BasicSemanticConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.SemanticReadItem;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.processor.SemanticIntegratorProcessor;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.reader.SemanticIntegratorReader;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.writer.SemanticIntegratorWriter;

/**
 * Created by Pawel on 2017-02-08.
 */
@Configuration
@ComponentScan("pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.*")
@EnableBatchProcessing
@EnableScheduling
@Import({ BasicMongoConfig.class, BasicSemanticConfig.class })
public class SemanticBatchConfiguration {
    private static final int MAX_ITEM_COUNT = 200000;
    private static final int PAGE_SIZE = 10000;
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SemanticIntegratorProcessor processor;

    @Autowired
    private SemanticIntegratorReader reader;

    @Autowired
    private SemanticIntegratorWriter bulkWriter;

    @Autowired
    private Job semanticExpressionJob;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SnapshotRepository snapshotRepository;

    // tag::readerwriterprocessor[]

    @Bean
    public RepositoryItemReader<Snapshot> pageableReader() {
        RepositoryItemReader<Snapshot> pageableReader = new RepositoryItemReader<>();
        pageableReader.setRepository(snapshotRepository);
        pageableReader.setPageSize(PAGE_SIZE);
        // pageableReader.setMaxItemCount(MAX_ITEM_COUNT);
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("id", Sort.Direction.ASC);
        pageableReader.setSort(sortMap);
        pageableReader.setSaveState(false);
        pageableReader.setMethodName("findAll");
        return pageableReader;
    }

    @Bean
    @StepScope
    public MongoItemReader<Snapshot> partitionedItemReader(@Value("#{stepExecutionContext[fromId]}") int fromId,
            @Value("#{stepExecutionContext[toId]}") int toId) {
        MongoItemReader<Snapshot> itemReader = new MongoItemReader<>();
        itemReader.setTemplate(mongoTemplate);
        itemReader.setTargetType(Snapshot.class);
        itemReader.setPageSize(PAGE_SIZE);
        itemReader.setCurrentItemCount(fromId);
        itemReader.setMaxItemCount(toId - fromId);
        itemReader.setMaxItemCount(MAX_ITEM_COUNT);
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("id", Sort.DEFAULT_DIRECTION);
        itemReader.setSort(sortMap);
        itemReader.setSaveState(true);
        itemReader.setQuery("{}");
        return itemReader;
    }

    @Bean
    public ItemWriter<HolonDto> writer() {
        MongoItemWriter<HolonDto> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("holon");
        return writer;
    }

    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job semanticExpressionJob(JobCompletionNotificationListener listener,
            StepExecutionListener stepExecutionListener,
            SnapshotRepository repository) {
        return jobBuilderFactory.get("semanticExpressionJob")
                                .incrementer(new RunIdIncrementer())
                                .listener(listener)
                                .flow(masterPartionerStep(stepExecutionListener, repository))
                                .end()
                                .build();
    }

    @Bean
    public Step masterPartionerStep(StepExecutionListener stepExecutionListener, SnapshotRepository repository) {
        return stepBuilderFactory.get("masterPartionerStep")
                                 .partitioner("semanticExpression", partitioner(repository))
                                 .step(semanticExpression(stepExecutionListener))
                                 .gridSize(10)
                                 .taskExecutor(taskExecutor())
                                 .build();
    }

    @Bean
    public Step semanticExpression(StepExecutionListener stepExecutionListener) {
        return stepBuilderFactory.get("semanticExpression")
                                 .listener(stepExecutionListener)
                                 .<SemanticReadItem, HolonDto> chunk(1)
                                 .reader(reader)
                                 .processor(processor)
                                 .writer(bulkWriter)
                                 .taskExecutor(semanticTaskExecutor())
                                 .throttleLimit(10)
                                 .build();
    }
    // end::jobstep[]

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier(value = "semanticDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

    /*
     * @Bean
     * public BatchConfigurer batchConfigurer(@Qualifier("semanticDataSource") DataSource dataSource){
     * return new DefaultBatchConfigurer(dataSource);
     * }
     */
    @Bean
    public JobRepository jobRepository(@Qualifier(value = "semanticDataSource") DataSource dataSource,
            @Qualifier(value = "semanticTransactionManager") PlatformTransactionManager transactionManager) throws Exception {
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
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public TaskExecutor semanticTaskExecutor() {
        return new SyncTaskExecutor();
    }

    @Bean
    public RangePartition partitioner(SnapshotRepository repository) {
        final RangePartition rangePartition = new RangePartition(repository);
        rangePartition.setMaxItemCount(MAX_ITEM_COUNT);
        return rangePartition;
    }

    // @Scheduled(cron = "0 0 0 * * ?")
    public void importSnapchotsTask() throws Exception {

        final LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Job Started at :" + dateTime);
        final JobParameters params = new JobParameters(ImmutableMap.of("currentDate",
                new JobParameter(Date.valueOf(dateTime.toLocalDate().minusDays(1)), false),
                "id",
                new JobParameter(new Random().nextLong(), true)));
        JobExecution execution = jobLauncher().run(semanticExpressionJob, params);

        System.out.println("Job finished with status :" + execution.getStatus());
    }

}
