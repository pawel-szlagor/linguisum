package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

import com.mysema.query.types.expr.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.common.converter.BooleanExpressionConverter;

/**
 * Created by Pawel on 2017-05-07.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
@Setter
public class Predicate {
    @Convert(converter = BooleanExpressionConverter.class)
    private BooleanExpression booleanExpression;
    private String verb;
    private String label;
    private String linguisticVariable;

    @Override
    public String toString() {
        return String.join(" ", linguisticVariable, verb, label);
    }

}
