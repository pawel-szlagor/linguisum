package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.time.LocalTime.ofSecondOfDay;
import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DAY_PHASE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.MemGradeService;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service(value = "dayPhasePredicateService")
public class DayPhasePredicateServiceImpl implements CategoryPredicateService {
    private final MemGradeService memGradeService;

    @Autowired
    public DayPhasePredicateServiceImpl(MemGradeService memGradeService) {
        this.memGradeService = memGradeService;
    }

    @Override
    public List<com.mysema.query.types.expr.BooleanExpression> createPossiblePredicates() {
        return memGradeService.findByProperty(DAY_PHASE.name())
                              .stream()
                              .map(p -> snapshot.time.between(ofSecondOfDay((long) p.getLowerBoundary()),
                                      ofSecondOfDay((long) p.getUpperBoundary())))
                              .collect(toList());
    }
}
