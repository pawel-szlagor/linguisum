package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.reader;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
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
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.values;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.dsl.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;

/**
 * Created by Pawel on 2017-02-09.
 */
@StepScope
@Component
public class SemanticIntegratorReader implements ItemReader<HolonDto>, InitializingBean {

    private AtomicLong counter = new AtomicLong(0);
    private Queue<HolonDto> holons = new PriorityQueue<>(1000000,
                                                         Comparator.comparing(HolonDto::getLevel, Comparator.naturalOrder())
                                                                   .thenComparing(Comparator.comparing(h -> h.getParent().getCardinality(),
                                                                           Comparator.nullsLast(Comparator.naturalOrder()))));
    private Map<CategoryPredicateTypes, List<BooleanExpression>> mapOfPredicates = new HashMap<>();
    private final EnumeratedDistribution<CategoryPredicateTypes> categoriesDistribution;
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
    private final SnapshotRepository snapshotRepository;

    @Autowired
    public SemanticIntegratorReader(@Qualifier(value = "windspeedPredicateService") CategoryPredicateService windspeedPredicateService,
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
            SnapshotRepository snapshotRepository) {
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
        this.snapshotRepository = snapshotRepository;
        categoriesDistribution = new EnumeratedDistribution(stream(values()).map(c -> new Pair(c, 1.0)).collect(toList()));
    }

    @Override
    public synchronized HolonDto read() throws InterruptedException {
        if (!holons.isEmpty()) {
            return holons.poll();
        } else if (holons.isEmpty() && counter.get() < 10) {
            /*
             * final List<CategoryPredicateTypes> randomCategories = stream(categoriesDistribution.sample(
             * CategoryPredicateTypes.values().length, new CategoryPredicateTypes[] {})).distinct().collect(toList());
             */
            final List<CategoryPredicateTypes> randomCategories = Arrays.stream(CategoryPredicateTypes.values()).collect(toList());
            Collections.shuffle(randomCategories);
            HolonDto root = HolonDto.builder().cardinality(snapshotRepository.count()).build();
            generateHolonsWithPredicatesForLevel(randomCategories, root, 0);
            addHolonsToQueue(root);
            return holons.poll();
        } else {
            return null;
        }
    }

    private void addHolonsToQueue(HolonDto root) {
        if (root.getChildren() != null && !root.getChildren().isEmpty()) {
            root.getChildren().forEach(h -> holons.add(h));
            root.getChildren().forEach(this::addHolonsToQueue);
        }
    }

    private void generateHolonsWithPredicatesForLevel(List<CategoryPredicateTypes> randomCategories, HolonDto root, int level) {
        if (level < 6) {
            root.addChildren(mapOfPredicates.get(randomCategories.get(level)), randomCategories.get(level));
            root.getChildren().forEach(c -> generateHolonsWithPredicatesForLevel(randomCategories, c, level + 1));
        }
    }

    @Override
    public void afterPropertiesSet() {
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
}
