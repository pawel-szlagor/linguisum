package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Created by Pawel on 2017-03-17.
 */
public interface CategoryPredicateService {
    List<BooleanExpression> createPossiblePredicates();
}
