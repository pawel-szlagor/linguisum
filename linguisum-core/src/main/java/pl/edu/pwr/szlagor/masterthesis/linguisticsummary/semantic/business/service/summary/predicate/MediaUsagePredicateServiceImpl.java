package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QPSnapshot.pSnapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType.values;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QMediaUsage;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.MemGradeService;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service(value = "mediaUsagePredicateService")
public class MediaUsagePredicateServiceImpl implements CategoryPredicateService {
    private final RoomRepository roomRepository;
    private final MemGradeService memGradeService;

    @Autowired
    public MediaUsagePredicateServiceImpl(RoomRepository roomRepository, MemGradeService memGradeService) {
        this.roomRepository = roomRepository;
        this.memGradeService = memGradeService;
    }

    @Override
    public List<BooleanExpression> createPossiblePredicates() {
        final List<Room> roomList = roomRepository.findAll();
        final MediaType[] mediaTypes = values();
        final List<QMediaUsage> qMediaUsageStream = Lists.newArrayList(pSnapshot.mediaUsages.mediaUsage1,
                pSnapshot.mediaUsages.mediaUsage2,
                pSnapshot.mediaUsages.mediaUsage3,
                pSnapshot.mediaUsages.mediaUsage4);
        IntStream.range(0, mediaTypes.length).forEach(i -> qMediaUsageStream.get(i).mediaType.eq(mediaTypes[i]));
        List<BooleanExpression> expressions = Lists.newArrayList();
        expressions.addAll(
                qMediaUsageStream.stream()
                                 .flatMap(qm -> memGradeService.findByProperty(mediaTypes[qMediaUsageStream.indexOf(qm)].name())
                                                               .stream()
                                                               .map(p -> qm.mediaType.eq(mediaTypes[qMediaUsageStream.indexOf(qm)]).and(

                                                                       qm.usagePerMinute.between(p.getLowerBoundary(),
                                                                               p.getUpperBoundary()))))
                                 .collect(Collectors.toList()));
        return expressions;
    }
}
