package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class PrecipitationLevels {
    private static final String PROPERTY_NAME = "precipitation";
    public static final TrapezoidalMemGradeDto DRY = TrapezoidalMemGradeDto.builder().lowerBoundary(-Double.MAX_VALUE).lowerExtremum(0).upperExtremum(0).upperBoundary(0.0).propertyName(PROPERTY_NAME).description("brak opadów").build();
    public static final TrapezoidalMemGradeDto LOW = TrapezoidalMemGradeDto.builder().lowerBoundary(0.0).lowerExtremum(2).upperExtremum(10).upperBoundary(15.0).propertyName(PROPERTY_NAME).description("niski opad").build();
    public static final TrapezoidalMemGradeDto MEDIUM = TrapezoidalMemGradeDto.builder().lowerBoundary(10.0).lowerExtremum(18).upperExtremum(25).upperBoundary(30.0).propertyName(PROPERTY_NAME).description("średni opad").build();
    public static final TrapezoidalMemGradeDto HIGH = TrapezoidalMemGradeDto.builder().lowerBoundary(22.0).lowerExtremum(27).upperExtremum(35).upperBoundary(MAX_VALUE).propertyName(PROPERTY_NAME).description("wysoki opad").build();
}
