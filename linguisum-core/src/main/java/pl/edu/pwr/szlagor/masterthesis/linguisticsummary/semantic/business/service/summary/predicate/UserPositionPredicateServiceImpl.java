package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Predicate> createPossiblePredicates() {
        final List<Predicate> booleanExpressions = personRepository.findAll()
                                                                   .stream()
                                                                   .flatMap(p -> roomRepository.findAll()
                                                                                               .stream()
                                                                                               .map(r -> Predicate.builder()
                                                                                                                  .booleanExpression(
                                                                                                                          snapshot.personStates.contains(
                                                                                                                                  new PersonState(p,
                                                                                                                                                  r)))
                                                                                                                  .verb("znajduje się w")
                                                                                                                  .label(r.getName())
                                                                                                                  .linguisticVariable(
                                                                                                                          p.getName())
                                                                                                                  .build()))
                                                                   .collect(Collectors.toList());
        booleanExpressions.add(Predicate.builder()
                                        .booleanExpression(snapshot.roomStates.isEmpty())
                                        .verb("nie znajduje się w")
                                        .linguisticVariable("nikt")
                                        .label("żadnym pokoju")
                                        .build());
        return booleanExpressions;
    }
}
