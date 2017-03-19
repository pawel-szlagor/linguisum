package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QRoomState.roomState;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DES_TEMP;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PersonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.MemGradeService;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service("roomStatePredicateService")
public class RoomStatePredicateServiceImpl implements CategoryPredicateService {
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;
    private final MemGradeService memGradeService;

    @Autowired
    public RoomStatePredicateServiceImpl(PersonRepository personRepository,
            RoomRepository roomRepository,
            MemGradeService memGradeService) {
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
        this.memGradeService = memGradeService;
    }

    @Override
    public List<BooleanExpression> createPossiblePredicates() {
        final Stream<BooleanExpression> booleanExpressionStream = roomRepository.findAll().stream().flatMap(
                r -> personRepository.findAll().stream().flatMap(p -> memGradeService.findByProperty(DES_TEMP.name())
                                                                                     .stream()
                                                                                     .map(t -> {
                                                                                         final Expression expression = roomState.room.id.eq(
                                                                                                 r.getId())
                                                                                                                                        .and(roomState.person.id.eq(
                                                                                                                                                p.getId()))
                                                                                                                                        .and(roomState.desiredTemp.between(
                                                                                                                                                t.getLowerBoundary(),
                                                                                                                                                t.getUpperBoundary()));
                                                                                         return snapshot.roomStates.contains(expression);
                                                                                     })));
        return booleanExpressionStream.collect(toList());
    }
}
