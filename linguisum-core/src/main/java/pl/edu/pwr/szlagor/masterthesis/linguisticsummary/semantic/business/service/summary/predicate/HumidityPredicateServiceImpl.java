package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.HUMIDITY;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.MemGradeService;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service(value = "humidityPredicateService")
public class HumidityPredicateServiceImpl implements CategoryPredicateService {
    private final MemGradeService memGradeService;

    @Autowired
    public HumidityPredicateServiceImpl(MemGradeService memGradeService) {
        this.memGradeService = memGradeService;
    }

    @Override
    public List<Predicate> createPossiblePredicates() {
        return memGradeService.findByProperty(HUMIDITY.name())
                              .stream()
                              .map(l -> Predicate.builder()
                                                 .booleanExpression(snapshot.weatherConditions.humidity.between(l.getLowerBoundary(),
                                                         l.getUpperBoundary()))
                                                 .label(l.getDescription())
                                                 .verb("jest")
                                                 .linguisticVariable("wilgotność")
                                                 .build())
                              .collect(toList());
    }
}
