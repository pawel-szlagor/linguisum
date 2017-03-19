package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.ELECTRICITY;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class ElectricityUsageLevels {
    private static final String PROPERTY_NAME = ELECTRICITY.name();
    public static final TrapezoidalMemGradeDto NONE = TrapezoidalMemGradeDto.builder().lowerBoundary(-Double.MAX_VALUE).lowerExtremum(0).upperExtremum(0).upperBoundary(0.0).propertyName(PROPERTY_NAME).description("brak").build();
    public static final TrapezoidalMemGradeDto LOW = TrapezoidalMemGradeDto.builder().lowerBoundary(0.0).lowerExtremum(5).upperExtremum(10).upperBoundary(20.0).propertyName(PROPERTY_NAME).description("niskie").build();
    public static final TrapezoidalMemGradeDto MEDIUM = TrapezoidalMemGradeDto.builder().lowerBoundary(10.0).lowerExtremum(15).upperExtremum(20).upperBoundary(25.0).propertyName(PROPERTY_NAME).description("średnie").build();
    public static final TrapezoidalMemGradeDto HIGH = TrapezoidalMemGradeDto.builder().lowerBoundary(20.0).lowerExtremum(25).upperExtremum(35).upperBoundary(45.0).propertyName(PROPERTY_NAME).description("wysokie").build();
    public static final TrapezoidalMemGradeDto VERY_HIGH = TrapezoidalMemGradeDto.builder().lowerBoundary(35.0).lowerExtremum(40).upperExtremum(55).upperBoundary(MAX_VALUE).propertyName(PROPERTY_NAME).description("bardzo wysokie").build();
}
