package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QPSnapshot.pSnapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.SUNLIGHT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.MemGradeService;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service("sunlightPredicateService")
public class SunlightPredicateServiceImpl implements CategoryPredicateService {
    private final MemGradeService memGradeService;

    @Autowired
    public SunlightPredicateServiceImpl(MemGradeService memGradeService) {
        this.memGradeService = memGradeService;
    }

    @Override
    public List<Predicate> createPossiblePredicates() {
        return memGradeService.findByProperty(SUNLIGHT.name())
                              .stream()
                              .map(l -> Predicate.builder()
                                                 .booleanExpression(pSnapshot.weatherConditions.sunlightEmission.between(
                                                         l.getLowerBoundary(), l.getUpperBoundary()))
                                                 .verb("występuje")
                                                 .linguisticVariable("")
                                                 .label(l.getDescription())
                                                 .build())
                              .collect(toList());
    }
}
