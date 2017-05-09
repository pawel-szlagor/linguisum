package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PSnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.Predicate;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-03-24.
 */
@Component
public class HolonCreatorTasklet implements Tasklet {
    private static final int MAX_HOLONS = 500000;
    private final HolonCache holonCache;
    private final List<CategoryPredicateTypes> factors = Lists.newArrayList(PERSON_STATE,
            // TEMP_OUT,
            // DEVICE_STATE,
            // SUNLIGHT,
            // WIND_SPEED,
            // PRECIPITATION_TYPE,
            // PRESSURE,
            // PRECIPITATION,
            // PRESSURE,
            HUMIDITY);
    private final CategoryPredicateTypes result = ROOM_STATE;
    private final PSnapshotRepository psnapshotRepository;
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
    private Map<CategoryPredicateTypes, List<Predicate>> mapOfPredicates = new HashMap<>();
    private final ProfilesCombinationsCache profilesCombinationsCache;
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
            PSnapshotRepository psnapshotRepository,
            MongoTemplate template,
            ProfilesCombinationsCache profilesCombinationsCache) {
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
        this.psnapshotRepository = psnapshotRepository;
        this.template = template;
        this.profilesCombinationsCache = profilesCombinationsCache;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        initializePredicates();
        // Collections.shuffle(factors);
        final List<List<CategoryPredicateTypes>> profilesCombinations = profilesCombinationsCache.getProfilesCombinations();
        if (profilesCombinations.isEmpty()) {
            System.out.println("Zakończono generacje kombinacji dla profilów.");
            return RepeatStatus.FINISHED;
        }
        final List<CategoryPredicateTypes> combination = profilesCombinationsCache.getProfilesCombinations().remove(0);
        Holon root = Holon.builder().cardinality(psnapshotRepository.count()).build();
        generateHolonsWithPredicatesForLevel(combination, root, 0);
        List<Holon> holonToSave = convertToEntites(root);
        while (holonToSave.size() > MAX_HOLONS) {
            root = Holon.builder().cardinality(root.getCardinality()).build();
            generateHolonsWithPredicatesForLevel(combination.subList(0, combination.size() - 1), root, 0);
            holonToSave = convertToEntites(root);
        }
        System.out.println("Stworzono: " + holonToSave.size() + " holonów");
        template.insert(holonToSave, Holon.class);
        // Query query = Query.query(Criteria.where("cardinality").lt(0.01d));
        // template.remove(query, Holon.class);
        holonCache.getRootHolons().clear();
        holonCache.getRootHolons().add(root);
        return RepeatStatus.FINISHED;
    }

    private void initializePredicates() {
        // mapOfPredicates.put(DAY_PHASE,
        // dayPhasePredicateService.createPossiblePredicates());
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

    private void generateHolonsWithPredicatesForLevel(List<CategoryPredicateTypes> factors, Holon root, int level) {
        if (level < factors.size()) {
            root.addChildren(mapOfPredicates.get(factors.get(level)), factors.get(level));
            root.getChildren().forEach(c -> generateHolonsWithPredicatesForLevel(factors, c, level + 1));
        }
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

}
