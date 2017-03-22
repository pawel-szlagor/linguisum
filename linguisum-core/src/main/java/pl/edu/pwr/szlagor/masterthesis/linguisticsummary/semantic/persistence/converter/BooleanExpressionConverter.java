package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.mongodb.util.JSON;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * Created by Pawel on 2017-03-18.
 */
@Converter
public class BooleanExpressionConverter implements AttributeConverter<BooleanExpression, String> {

    @Override
    public String convertToDatabaseColumn(BooleanExpression attribute) {
        return JSON.serialize(attribute);
    }

    @Override
    public BooleanExpression convertToEntityAttribute(String dbData) {
        return (BooleanExpression) JSON.parse(dbData);
    }
}
