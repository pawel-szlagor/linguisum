package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.converter;

import org.springframework.core.convert.converter.Converter;

import com.mysema.query.types.expr.BooleanExpression;

/**
 * Created by Pawel on 2017-03-18.
 */
public class StringToBooleanExpressionConverter implements Converter<String, BooleanExpression> {

    @Override
    public BooleanExpression convert(String source) {
        return null;
    }
}
