package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.common.converter;

import org.springframework.core.convert.converter.Converter;

import com.mysema.query.types.expr.BooleanExpression;

/**
 * Created by Pawel on 2017-03-18.
 */
@javax.persistence.Converter
public class StringToBooleanExpressionConverter implements Converter<String, BooleanExpression> {

    @Override
    public BooleanExpression convert(String source) {
        return null;
    }
}
