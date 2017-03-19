package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.WIND_SPEED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.MemGradeService;

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
    public List<BooleanExpression> createPossiblePredicates() {
        return memGradeService.findByProperty(WIND_SPEED.name())
                              .stream()
                              .map(p -> snapshot.weatherConditions.windSpeed.between(p.getLowerBoundary(), p.getUpperBoundary()))
                              .collect(toList());
    }
}
