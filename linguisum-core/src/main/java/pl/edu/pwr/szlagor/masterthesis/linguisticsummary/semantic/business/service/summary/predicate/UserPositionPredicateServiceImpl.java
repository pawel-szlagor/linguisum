package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.QFSnapshot.fSnapshot;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.expr.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PersonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service("userPositionPredicateService")
public class UserPositionPredicateServiceImpl implements CategoryPredicateService {
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public UserPositionPredicateServiceImpl(PersonRepository personRepository, RoomRepository roomRepository) {
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true, value = "semanticTransactionManager")
    @Override
    public List<com.mysema.query.types.expr.BooleanExpression> createPossiblePredicates() {
        final List<BooleanExpression> booleanExpressions = personRepository.findAll()
                                                                           .stream()
                                                                           .flatMap(p -> roomRepository.findAll().stream().map(
                                                                                   r -> fSnapshot.personStates.contains(
                                                                                           new PersonState(p, r))))
                                                                           .collect(Collectors.toList());
        booleanExpressions.add(fSnapshot.roomStates.isEmpty());
        return booleanExpressions;
    }
}
