package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.config;

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
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
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
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.common.collect.ImmutableMap;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.common.job.StepExecutionListener;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.converter.PSnapshotConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.config.BasicSemanticConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.processor.PSnapshotIntegratorProcessor;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.processor.SemanticIntegratorProcessor;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.reader.SemanticIntegratorReader;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.reader.SemanticRepositoryItemReader;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet.HolonCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet.HolonCreatorTasklet;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet.ProfileToPredicatesTransformerTasklet;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet.ProfilesCombinationsCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.writer.SemanticIntegratorWriter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-02-08.
 */
@Configuration
@ComponentScan({ "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.*",
                 "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.common.*" })
@EnableBatchProcessing
@EnableScheduling
@Import({ BasicMongoConfig.class, BasicSemanticConfig.class })
public class SemanticBatchConfiguration {
    private static final Integer MAX_ITEM_COUNT = 1000000;
    private static final int PAGE_SIZE = 10000;
    private static final int GRID_SIZE = 20;
    private static final int CHUNK_SIZE = 100;
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SemanticIntegratorReader reader;

    @Autowired
    private SemanticIntegratorProcessor processor;

    @Autowired
    private SemanticIntegratorWriter writer;

    @Autowired
    private Job semanticExpressionJob;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SnapshotRepository snapshotRepository;

    // tag::readerwriterprocessor[]

    @Bean
    public ItemReader<Snapshot> pageableReader() {
        MongoItemReader<Snapshot> pageableReader = new MongoItemReader<Snapshot>();
        pageableReader.setPageSize(PAGE_SIZE);
        pageableReader.setTargetType(Snapshot.class);
        pageableReader.setMaxItemCount(MAX_ITEM_COUNT);
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("id", Sort.Direction.ASC);
        pageableReader.setSort(sortMap);
        pageableReader.setSaveState(false);
        pageableReader.setTemplate(mongoTemplate);
        pageableReader.setQuery("{}");
        return pageableReader;
    }

    @Bean
    @StepScope
    public ItemProcessor<Snapshot, PSnapshot> pSnapshotItemProcessor(PSnapshotConverter pSnapshotConverter) {
        return new PSnapshotIntegratorProcessor(pSnapshotConverter);
    }

    @Bean
    @StepScope
    public ItemReader<Snapshot> partitionedItemReader(@Value("#{stepExecutionContext[startPage]}") int startPage,
            @Value("#{stepExecutionContext[endPage]}") int endPage) {
        SemanticRepositoryItemReader<Snapshot> itemReader = new SemanticRepositoryItemReader<>();
        itemReader.setRepository(snapshotRepository);
        itemReader.setMethodName("findAll");
        itemReader.setPageSize(PAGE_SIZE);
        itemReader.setSaveState(true);
        itemReader.setPage(startPage);
        itemReader.setEndPage(endPage);
        return itemReader;
    }

    @Bean
    public ItemWriter<Holon> writer() {
        MongoItemWriter<Holon> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("holon");
        return writer;
    }

    @Bean
    public ItemWriter<PSnapshot> pSnapshotWriter() {
        MongoItemWriter<PSnapshot> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("psnapshot");
        return writer;
    }

    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job semanticExpressionJob(Step semanticExpressionCreator, Step semanticExpression, Step profilesPredicatesCreator) {
        return jobBuilderFactory.get("semanticExpressionJob")
                                .incrementer(new RunIdIncrementer())
                                .start(profilesPredicatesCreator)
                                .next(semanticExpressionCreator)
                                .next(semanticExpression)
                                .on("REPEAT")
                                .to(semanticExpressionCreator)
                                .on("COMPLETED")
                                .end()
                                .build()
                                .build();
    }

    /*
     * @Bean
     * public Job semanticExpressionJob(Step pSnapshotImport) {
     * return jobBuilderFactory.get("pSnapshotImportJob").incrementer(new
     * RunIdIncrementer()).start(pSnapshotImport).build();
     * }
     */

    @Bean
    public Step masterPartionerStep(StepExecutionListener stepExecutionListener, SnapshotRepository repository) {
        return stepBuilderFactory.get("masterPartionerStep")
                                 .partitioner("semanticExpression", partitioner(repository))
                                 .step(semanticExpression(stepExecutionListener))
                                 .gridSize(GRID_SIZE)
                                 .taskExecutor(taskExecutor())
                                 .build();
    }

    @Bean
    public Step semanticExpression(StepExecutionListener stepExecutionListener) {
        return stepBuilderFactory.get("semanticExpression")
                                 .listener(stepExecutionListener)
                                 .<Holon, Holon> chunk(CHUNK_SIZE)
                                 .reader(reader)
                                 .processor(processor)
                                 .writer(writer)
                                 .taskExecutor(semanticTaskExecutor())
                                 .throttleLimit(2)
                                 .build();
    }

    @Bean
    public Step pSnapshotImport(ItemReader<? extends Snapshot> pageableReader,
            ItemWriter<? super PSnapshot> pSnapshotWriter,
            ItemProcessor<? super Snapshot, ? extends PSnapshot> pSnapshotItemProcessor) {
        return stepBuilderFactory.get("pSnapshotImport")
                                 .<Snapshot, PSnapshot> chunk(1000)
                                 .reader(pageableReader)
                                 .processor(pSnapshotItemProcessor)
                                 .writer(pSnapshotWriter)
                                 .build();
    }

    @Bean
    public Step semanticExpressionCreator(HolonCreatorTasklet holonCreatorTasklet) {
        return stepBuilderFactory.get("semanticExpressionCreator").tasklet(holonCreatorTasklet).build();
    }

    @Bean
    public Step profilesPredicatesCreator(ProfileToPredicatesTransformerTasklet profileToPredicatesTransformerTasklet) {
        return stepBuilderFactory.get("profileToPredicatesTransformerTasklet").tasklet(profileToPredicatesTransformerTasklet).build();
    }

    // end::jobstep[]
    @Bean
    public HolonCache holonCache() {
        return new HolonCache();
    }

    @Bean
    public ProfilesCombinationsCache profilesCombinationsCache() {
        return new ProfilesCombinationsCache();
    }

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
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public RangePartition partitioner(SnapshotRepository repository) {
        final RangePartition rangePartition = new RangePartition(repository, profilesCombinationsCache());
        // rangePartition.setMaxItemCount(MAX_ITEM_COUNT);
        rangePartition.setGridSize(GRID_SIZE);
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
