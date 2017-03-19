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
    public List<com.mysema.query.types.expr.BooleanExpression> createPossiblePredicates() {
        return stream(WeatherEvent.values()).map(snapshot.weatherConditions.weatherEvents::contains).collect(toList());
    }
}
