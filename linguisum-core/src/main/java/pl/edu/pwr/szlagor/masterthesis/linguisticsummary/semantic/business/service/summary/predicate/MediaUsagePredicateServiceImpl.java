package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QMediaUsage.mediaUsage;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType.values;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.MemGradeService;

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
        return roomRepository.findAll()
                             .stream()
                             .flatMap(r -> stream(values()).flatMap(m -> memGradeService.findByProperty(m.name()).stream().map(
                                     p -> snapshot.mediaUsages.any().location.eq(r).and(
                                             mediaUsage.mediaType.eq(m).and(mediaUsage.usagePerMinute.between(p.getLowerBoundary(),
                                                     p.getUpperBoundary()))))))
                             .collect(toList());
    }
}
