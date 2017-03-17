package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class WaterUsageLevels {
    private static final String PROPERTY_NAME = "water.usage";
    public static final TrapezoidalMemGradeDto NONE = TrapezoidalMemGradeDto.builder().lowerBoundary(-Double.MAX_VALUE).lowerExtremum(0).upperExtremum(0.5).upperBoundary(1.0).propertyName(PROPERTY_NAME).description("brak").build();
    public static final TrapezoidalMemGradeDto LOW = TrapezoidalMemGradeDto.builder().lowerBoundary(0.0).lowerExtremum(0.5).upperExtremum(2.5).upperBoundary(3.5).propertyName(PROPERTY_NAME).description("niskie").build();
    public static final TrapezoidalMemGradeDto MEDIUM = TrapezoidalMemGradeDto.builder().lowerBoundary(3.0).lowerExtremum(3.5).upperExtremum(5.5).upperBoundary(6.5).propertyName(PROPERTY_NAME).description("średnie").build();
    public static final TrapezoidalMemGradeDto HIGH = TrapezoidalMemGradeDto.builder().lowerBoundary(6.0).lowerExtremum(6.5).upperExtremum(9.5).upperBoundary(11.0).propertyName(PROPERTY_NAME).description("wysokie").build();
    public static final TrapezoidalMemGradeDto VERY_HIGH = TrapezoidalMemGradeDto.builder().lowerBoundary(10.0).lowerExtremum(12.5).upperExtremum(15.0).upperBoundary(MAX_VALUE).propertyName(PROPERTY_NAME).description("bardzo wysokie").build();
}
