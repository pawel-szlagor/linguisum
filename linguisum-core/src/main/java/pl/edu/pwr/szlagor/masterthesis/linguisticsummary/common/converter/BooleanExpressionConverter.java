package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mysema.query.types.expr.BooleanExpression;

/**
 * Created by Pawel on 2017-03-18.
 */
@Component
public class BooleanExpressionConverter implements Converter<BooleanExpression, String> {

    @Override
    public String convert(BooleanExpression source) {
        return source.toString();
    }
}
