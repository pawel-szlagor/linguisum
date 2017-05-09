package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.time.LocalTime.ofSecondOfDay;
import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QPSnapshot.pSnapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DAY_PHASE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Predicate> createPossiblePredicates() {
        return memGradeService.findByProperty(DAY_PHASE.name())
                              .stream()
                              .map(p -> Predicate.builder()
                                                 .booleanExpression(
                                                         (pSnapshot.time.hour().gt(ofSecondOfDay((long) p.getLowerBoundary()).getHour()).or(
                                                                 pSnapshot.time.hour()
                                                                               .goe(ofSecondOfDay((long) p.getLowerBoundary()).getHour())
                                                                               .and(pSnapshot.time.minute().goe(ofSecondOfDay(
                                                                                       (long) p.getLowerBoundary()).getMinute())))).and(
                                                                                               (pSnapshot.time.hour().lt(ofSecondOfDay(
                                                                                                       (long) p.getLowerBoundary()).getHour())).or(
                                                                                                               pSnapshot.time.hour()
                                                                                                                             .loe(ofSecondOfDay(
                                                                                                                                     (long) p.getLowerBoundary()).getHour())
                                                                                                                             .and(pSnapshot.time.minute()
                                                                                                                                                .loe(ofSecondOfDay(
                                                                                                                                                        (long) p.getLowerBoundary()).getMinute())))))
                                                 .label(p.getDescription())
                                                 .linguisticVariable("")
                                                 .verb("jest")
                                                 .build())
                              .collect(toList());
    }
}
