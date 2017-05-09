package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QPSnapshot.pSnapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.WIND_SPEED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.MemGradeService;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service("windspeedPredicateService")
public class WindspeedPredicateServiceImpl implements CategoryPredicateService {
    private final MemGradeService memGradeService;

    @Autowired
    public WindspeedPredicateServiceImpl(MemGradeService memGradeService) {
        this.memGradeService = memGradeService;
    }

    @Override
    public List<Predicate> createPossiblePredicates() {
        return memGradeService.findByProperty(WIND_SPEED.name())
                              .stream()
                              .map(l -> Predicate.builder()
                                                 .booleanExpression(pSnapshot.weatherConditions.windSpeed.between(l.getLowerBoundary(),
                                                         l.getUpperBoundary()))
                                                 .verb("jest")
                                                 .linguisticVariable("na zewnątrz")
                                                 .label(l.getDescription())
                                                 .build())
                              .collect(toList());
    }
}
