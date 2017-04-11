package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QPSnapshot.pSnapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DES_TEMP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mysema.query.types.Expression;
import com.mysema.query.types.expr.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QRoomState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PersonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.MemGradeService;

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
        final List<Room> roomList = roomRepository.findAll();
        roomList.sort(Comparator.comparingLong(Room::getId));
        final ArrayList<QRoomState> roomStates = Lists.newArrayList(pSnapshot.roomStates.roomState1,
                pSnapshot.roomStates.roomState2,
                pSnapshot.roomStates.roomState3,
                pSnapshot.roomStates.roomState4,
                pSnapshot.roomStates.roomState5,
                pSnapshot.roomStates.roomState6,
                pSnapshot.roomStates.roomState7,
                pSnapshot.roomStates.roomState8,
                pSnapshot.roomStates.roomState9);
        List<BooleanExpression> expressions = Lists.newArrayList();

        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState1);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState2);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState3);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState4);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState5);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState6);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState7);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState8);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState9);
        addExpressionForRoomState(roomList, expressions, pSnapshot.roomStates.roomState10);
        return expressions;
    }

    private void addExpressionForRoomState(List<Room> roomList, List<BooleanExpression> expressions, QRoomState roomState1) {

        final Stream<BooleanExpression> booleanExpressionStream = personRepository.findAll().stream().flatMap(
                p -> memGradeService.findByProperty(DES_TEMP.name()).stream().map(t -> {
                    final Expression expression = QRoomState.roomState.desiredTemp.between(t.getLowerBoundary(), t.getUpperBoundary())
                                                                                  .and(QRoomState.roomState.room.eq(roomList.get(0)));
                    return roomState1.eq(expression);
                }));
        expressions.addAll(booleanExpressionStream.collect(Collectors.toList()));
    }
}
