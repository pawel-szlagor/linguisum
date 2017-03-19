package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.similarity.impl;

import static org.apache.commons.lang3.tuple.Pair.of;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.tuple.Pair;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.querydsl.core.types.dsl.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.business.snapshot.SnapshotService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.SnapshotDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.similarity.SimilarityService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon.HolonService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.config.BasicSemanticConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.HolonRepository;

/**
 * Created by Pawel on 2017-03-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BasicMongoConfig.class, BasicSemanticConfig.class, }, loader = AnnotationConfigContextLoader.class)
public class SimilarityServiceImplTest {

    private static final LocalDateTime INITIAL_TIME = LocalDate.of(2016, 1, 1).atStartOfDay();
    @Autowired
    private SnapshotService snapshotService;
    @Autowired
    private SnapshotRepository snapshotRepository;

    @Autowired
    private SimilarityService similarityService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HolonService holonService;
    @Autowired
    private HolonRepository holonRepository;

    @Test
    public void shouldCalculateSimilarityForEqualObjects() {
        //given
        final SnapshotDto snapshotDto = snapshotService.findByLocalDateTime(INITIAL_TIME.plusSeconds(1500));
        final SnapshotDto snapshotDto2 = snapshotService.findByLocalDateTime(INITIAL_TIME.plusSeconds(1500));
        //when
        Double similarity = null;
        for (int i = 0; i < 20; i++) {
            similarity = similarityService.calculateSimilarity(snapshotDto, snapshotDto2);
        }//then
        assertThat(similarity, Matchers.equalTo(0.0));
    }

    @Test
    public void shouldFindByExample() {
        // given
        final BooleanExpression expression = snapshot.personStates.contains(PersonState.builder().locationId(1L).userId(1L).build());
        BooleanExpression predicate = snapshot.date.after(LocalDate.of(2016, 1, 1)).and(snapshot.date.before(LocalDate.of(2016, 2, 1)));
        Aggregation agg = newAggregation(getMatchOperation(), getGroupOperation(), getProjectOperation());
        // when
        final List<TempCount> mappedResults = mongoTemplate.aggregate(agg, Snapshot.class, TempCount.class).getMappedResults();
        final Map<Pair<Room, Double>, Long> collect = StreamSupport.stream(snapshotRepository.findAll(predicate).spliterator(), true).map(Snapshot::getRoomStates).flatMap(Set::stream).collect(Collectors.groupingBy(l -> of(l.getRoom(), Math.round(l.getDesiredTemp() / 2) * 2.0), Collectors.counting()));
        // then
        System.out.println(collect);
    }

    private ProjectionOperation getProjectOperation() {
        return project("nCount", "nTempOut").and("roomStates.room").as("roomName").and("roomStates.desiredTemp").as("desiredTemp");
    }

    private GroupOperation getGroupOperation() {
        return group("roomStates.room", "roomStates.desiredTemp").addToSet("weatherConditions.tempOut").as("nTempOut").count().as("nCount");
    }

    private MatchOperation getMatchOperation() {
        return match(Criteria.where("date").gte(LocalDate.of(2016, 1, 1)).lte(LocalDate.of(2016, 2, 1)));
    }

    class TempCount {
        private Double nTempOut;
        private Double nCount;
        private Room roomName;
        private Double desiredTemp;
    }

    @Test
    public void shouldSaveHolonWithParent() {
        // given
        final BooleanExpression after = QSnapshot.snapshot.date.after(LocalDate.of(2016, 5, 1));
        HolonDto parent = HolonDto.builder().predicate(after).cardinality(10L).build();
        final BooleanExpression contains = QSnapshot.snapshot.personStates.contains(
                PersonState.builder().locationId(1L).userId(2L).build());
        HolonDto child = HolonDto.builder().cardinality(6L).predicate(contains).parent(parent).build();
        // when
        // holonService.save(child);
        holonRepository.findByRelevanceBetween(0.4, 1.0);
        // then
        holonService.findAll();
    }
}