package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.time.LocalTime.ofSecondOfDay;
import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DAY_PHASE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.QFSnapshot.fSnapshot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.MemGradeService;

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
    public List<BooleanExpression> createPossiblePredicates() {
        return memGradeService.findByProperty(DAY_PHASE.name())
                              .stream()
                              .map(p -> fSnapshot.time.between(ofSecondOfDay((long) p.getLowerBoundary()),
                                      ofSecondOfDay((long) p.getUpperBoundary())))
                              .collect(toList());
    }
}
