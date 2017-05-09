package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service("precipitationTypePredicateService")
public class PrecipitationTypePredicateServiceImpl implements CategoryPredicateService {

    @Override
    public List<Predicate> createPossiblePredicates() {
        final List<Predicate> expressions = stream(WeatherEvent.values()).map(
                e -> Predicate.builder()
                              .booleanExpression(snapshot.weatherConditions.weatherEvents.contains(e))
                              .linguisticVariable("")
                              .verb("występuje")
                              .label(e.getKey())
                              .build()).collect(toList());
        expressions.add(Predicate.builder()
                                 .booleanExpression(snapshot.weatherConditions.weatherEvents.isEmpty())
                                 .linguisticVariable("")
                                 .verb("brak")
                                 .label("opadów")
                                 .build());
        return expressions;
    }
}
