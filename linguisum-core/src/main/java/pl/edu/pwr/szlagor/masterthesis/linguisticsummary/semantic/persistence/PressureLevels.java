package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.PRESSURE;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class PressureLevels {
    private static final String PROPERTY_NAME = PRESSURE.name();
    public static final TrapezoidalMemGradeDto LOW = TrapezoidalMemGradeDto.builder().lowerBoundary(-Double.MAX_VALUE).lowerExtremum(9000).upperExtremum(1000).upperBoundary(1005).propertyName(PROPERTY_NAME).description("niskie").build();
    public static final TrapezoidalMemGradeDto MEDIUM = TrapezoidalMemGradeDto.builder().lowerBoundary(1000).lowerExtremum(1008).upperExtremum(1015).upperBoundary(1020).propertyName(PROPERTY_NAME).description("średnie").build();
    public static final TrapezoidalMemGradeDto HIGH = TrapezoidalMemGradeDto.builder().lowerBoundary(1012).lowerExtremum(1018).upperExtremum(1025).upperBoundary(1035).propertyName(PROPERTY_NAME).description("wysokie").build();
    public static final TrapezoidalMemGradeDto VERY_HIGH = TrapezoidalMemGradeDto.builder().lowerBoundary(1030).lowerExtremum(1040).upperExtremum(1050).upperBoundary(MAX_VALUE).propertyName(PROPERTY_NAME).description("bardzo wysokie").build();
}
