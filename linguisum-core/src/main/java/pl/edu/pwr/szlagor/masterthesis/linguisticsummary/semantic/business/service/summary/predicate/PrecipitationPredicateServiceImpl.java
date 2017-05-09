package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QPSnapshot.pSnapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.PRECIPITATION;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.MemGradeService;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service(value = "precipitationPredicateService")
public class PrecipitationPredicateServiceImpl implements CategoryPredicateService {
    private final MemGradeService memGradeService;

    @Autowired
    public PrecipitationPredicateServiceImpl(MemGradeService memGradeService) {
        this.memGradeService = memGradeService;
    }

    @Override
    public List<Predicate> createPossiblePredicates() {
        return memGradeService.findByProperty(PRECIPITATION.name())
                              .stream()
                              .map(l -> Predicate.builder()
                                                 .booleanExpression(pSnapshot.weatherConditions.precipitation.between(l.getLowerBoundary(),
                                                         l.getUpperBoundary()))
                                                 .linguisticVariable("")
                                                 .verb("występuje")
                                                 .label(l.getDescription())
                                                 .build())
                              .collect(toList());
    }
}
