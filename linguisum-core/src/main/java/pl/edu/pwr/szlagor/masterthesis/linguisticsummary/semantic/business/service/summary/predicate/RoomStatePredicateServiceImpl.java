package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DES_TEMP;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.QFSnapshot.fSnapshot;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.mysema.query.types.expr.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PersonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.FRoomState;
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
    public List<com.mysema.query.types.expr.BooleanExpression> createPossiblePredicates() {
        final Stream<BooleanExpression> booleanExpressionStream = roomRepository.findAll().stream().flatMap(
                r -> personRepository.findAll().stream().flatMap(p -> memGradeService.findByProperty(DES_TEMP.name())
                                                                                     .stream()
                                                                                     .map(t -> fSnapshot.roomStates.contains(
                                                                                             new FRoomState(r, p, Sets.newHashSet(t),
                                                                                                            null)))));
        return booleanExpressionStream.collect(toList());
    }
}
