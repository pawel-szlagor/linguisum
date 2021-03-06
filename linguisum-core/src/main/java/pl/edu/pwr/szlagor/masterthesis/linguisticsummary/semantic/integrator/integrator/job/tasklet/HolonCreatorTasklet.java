package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.tasklet;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.DEVICE_STATE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.HUMIDITY;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.MEDIA_USAGE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PERSON_STATE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PRECIPITATION;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PRECIPITATION_TYPE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PRESSURE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.ROOM_STATE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.SUNLIGHT;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.TEMP_OUT;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.WIND_SPEED;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-03-24.
 */
@Component
public class HolonCreatorTasklet implements Tasklet {
    private final HolonCache holonCache;
    private final List<CategoryPredicateTypes> factors = Lists.newArrayList(PERSON_STATE,
            TEMP_OUT,
            DEVICE_STATE,
            SUNLIGHT,
            WIND_SPEED,
            PRECIPITATION_TYPE);
    private final CategoryPredicateTypes result = ROOM_STATE;
    private final SnapshotRepository snapshotRepository;
    private final MongoTemplate template;
    private final CategoryPredicateService dayPhasePredicateService;
    private final CategoryPredicateService deviceStatePredicateService;
    private final CategoryPredicateService humidityPredicateService;
    private final CategoryPredicateService mediaUsagePredicateService;
    private final CategoryPredicateService precipitationPredicateService;
    private final CategoryPredicateService precipitationTypePredicateService;
    private final CategoryPredicateService pressurePredicateService;
    private final CategoryPredicateService sunlightPredicateService;
    private final CategoryPredicateService tempOutPredicateService;
    private final CategoryPredicateService userPositionPredicateService;
    private final CategoryPredicateService windspeedPredicateService;
    private final CategoryPredicateService roomStatePredicateService;
    private Map<CategoryPredicateTypes, List<BooleanExpression>> mapOfPredicates = new HashMap<>();
    private Integer maxItemsCount;
    private Random random = new Random();

    @Autowired
    public HolonCreatorTasklet(@Qualifier(value = "windspeedPredicateService") CategoryPredicateService windspeedPredicateService,
            @Qualifier(value = "userPositionPredicateService") CategoryPredicateService userPositionPredicateService,
            @Qualifier(value = "tempOutPredicateService") CategoryPredicateService tempOutPredicateService,
            @Qualifier(value = "sunlightPredicateService") CategoryPredicateService sunlightPredicateService,
            @Qualifier(value = "pressurePredicateService") CategoryPredicateService pressurePredicateService,
            @Qualifier(value = "precipitationTypePredicateService") CategoryPredicateService precipitationTypePredicateService,
            @Qualifier(value = "precipitationPredicateService") CategoryPredicateService precipitationPredicateService,
            @Qualifier(value = "dayPhasePredicateService") CategoryPredicateService dayPhasePredicateService,
            @Qualifier(value = "deviceStatePredicateService") CategoryPredicateService deviceStatePredicateService,
            @Qualifier(value = "humidityPredicateService") CategoryPredicateService humidityPredicateService,
            @Qualifier(value = "mediaUsagePredicateService") CategoryPredicateService mediaUsagePredicateService,
            @Qualifier(value = "roomStatePredicateService") CategoryPredicateService roomStatePredicateService,
            HolonCache holonCache,
            SnapshotRepository snapshotRepository,
            MongoTemplate template) {
        this.windspeedPredicateService = windspeedPredicateService;
        this.userPositionPredicateService = userPositionPredicateService;
        this.tempOutPredicateService = tempOutPredicateService;
        this.sunlightPredicateService = sunlightPredicateService;
        this.pressurePredicateService = pressurePredicateService;
        this.precipitationTypePredicateService = precipitationTypePredicateService;
        this.precipitationPredicateService = precipitationPredicateService;
        this.dayPhasePredicateService = dayPhasePredicateService;
        this.deviceStatePredicateService = deviceStatePredicateService;
        this.humidityPredicateService = humidityPredicateService;
        this.mediaUsagePredicateService = mediaUsagePredicateService;
        this.roomStatePredicateService = roomStatePredicateService;
        this.holonCache = holonCache;
        this.snapshotRepository = snapshotRepository;
        this.template = template;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        initializePredicates();
        for (int i = 0; i < 1; i++) {
            int firstIndex = random.nextInt(factors.size() - 3);
            int secondIndex = random.nextInt(factors.size() - 3) + 3;
            // final List<CategoryPredicateTypes> subList = factors.subList(Math.min(firstIndex, secondIndex),
            // Math.max(firstIndex, secondIndex));
            final List<CategoryPredicateTypes> subList = factors.subList(0, 4);
            Collections.shuffle(subList);
            Holon root = Holon.builder()
                              .cardinality(new AtomicLong(0))
                              /*
                               * .cardinality(
                               * maxItemsCount != null ? new AtomicLong(maxItemsCount) : new
                               * AtomicLong(snapshotRepository.count()))
                               */
                              .build();
            generateHolonsWithPredicatesForLevel(subList, result, root, 0);
            // List<Holon> holonToSave = convertToEntites(root);
            // holonToSave.forEach(template::save);
            holonCache.getRootHolons().add(root);
        }
        return RepeatStatus.FINISHED;
    }

    private void initializePredicates() {
        // mapOfPredicates.put(DAY_PHASE, dayPhasePredicateService.createPossiblePredicates());
        mapOfPredicates.put(DEVICE_STATE, deviceStatePredicateService.createPossiblePredicates());
        mapOfPredicates.put(HUMIDITY, humidityPredicateService.createPossiblePredicates());
        mapOfPredicates.put(MEDIA_USAGE, mediaUsagePredicateService.createPossiblePredicates());
        mapOfPredicates.put(PRECIPITATION, precipitationPredicateService.createPossiblePredicates());
        mapOfPredicates.put(PRECIPITATION_TYPE, precipitationTypePredicateService.createPossiblePredicates());
        mapOfPredicates.put(PRESSURE, pressurePredicateService.createPossiblePredicates());
        mapOfPredicates.put(SUNLIGHT, sunlightPredicateService.createPossiblePredicates());
        mapOfPredicates.put(TEMP_OUT, tempOutPredicateService.createPossiblePredicates());
        mapOfPredicates.put(PERSON_STATE, userPositionPredicateService.createPossiblePredicates());
        mapOfPredicates.put(WIND_SPEED, windspeedPredicateService.createPossiblePredicates());
        mapOfPredicates.put(ROOM_STATE, roomStatePredicateService.createPossiblePredicates());
    }

    private void generateHolonsWithPredicatesForLevel(List<CategoryPredicateTypes> factors,
            CategoryPredicateTypes result,
            Holon root,
            int level) {
        if (level < factors.size()) {
            root.addChildren(mapOfPredicates.get(factors.get(level)), (factors.get(level)));
            root.getChildren().forEach(c -> generateHolonsWithPredicatesForLevel(factors, result, c, level + 1));
        }
        root.addChildren(mapOfPredicates.get(result), result);
    }

    private List<Holon> convertToEntites(Holon root) {
        List<Holon> entites = Lists.newArrayListWithExpectedSize(root.count());
        addEntityToList(root, entites);
        return entites;
    }

    private void addEntityToList(Holon root, List<Holon> entites) {
        entites.add(root);
        if (root.getChildren() != null) {
            root.getChildren().forEach(c -> addEntityToList(c, entites));
        }
    }

    public void setMaxItemsCount(Integer maxItemsCount) {
        this.maxItemsCount = maxItemsCount;
    }
}
