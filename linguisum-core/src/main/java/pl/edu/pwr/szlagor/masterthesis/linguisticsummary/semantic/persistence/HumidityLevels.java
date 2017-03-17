package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class HumidityLevels {
    private static final String PROPERTY_NAME = "humidity";
    public static final TrapezoidalMemGradeDto VERY_DRY = TrapezoidalMemGradeDto.builder().lowerBoundary(-Double.MAX_VALUE).lowerExtremum(25).upperExtremum(35).upperBoundary(40.0).propertyName(PROPERTY_NAME).description("bardzo suche").build();
    public static final TrapezoidalMemGradeDto DRY = TrapezoidalMemGradeDto.builder().lowerBoundary(35.0).lowerExtremum(40).upperExtremum(55).upperBoundary(60).propertyName(PROPERTY_NAME).description("suche").build();
    public static final TrapezoidalMemGradeDto MEDIUM = TrapezoidalMemGradeDto.builder().lowerBoundary(55.0).lowerExtremum(65).upperExtremum(75).upperBoundary(80.0).propertyName(PROPERTY_NAME).description("normalne").build();
    public static final TrapezoidalMemGradeDto HUMID = TrapezoidalMemGradeDto.builder().lowerBoundary(75.0).lowerExtremum(80).upperExtremum(85).upperBoundary(90.0).propertyName(PROPERTY_NAME).description("wilgotne").build();
    public static final TrapezoidalMemGradeDto VERY_HUMID = TrapezoidalMemGradeDto.builder().lowerBoundary(85.0).lowerExtremum(90).upperExtremum(95).upperBoundary(MAX_VALUE).propertyName(PROPERTY_NAME).description("bardzo wilgotne").build();
}
