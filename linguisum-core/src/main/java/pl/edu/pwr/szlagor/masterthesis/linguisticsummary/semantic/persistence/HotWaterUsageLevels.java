package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.HOT_WATER;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class HotWaterUsageLevels {
    private static final String PROPERTY_NAME = HOT_WATER.name();
    public static final TrapezoidalMemGradeDto NONE = TrapezoidalMemGradeDto.builder()
                                                                            .lowerBoundary(-Double.MAX_VALUE)
                                                                            .lowerExtremum(0)
                                                                            .upperExtremum(0.3)
                                                                            .upperBoundary(0.7)
                                                                            .propertyName(PROPERTY_NAME)
                                                                            .description("brak")
                                                                            .build();
    public static final TrapezoidalMemGradeDto LOW = TrapezoidalMemGradeDto.builder()
                                                                           .lowerBoundary(0.0)
                                                                           .lowerExtremum(0.6)
                                                                           .upperExtremum(1.7)
                                                                           .upperBoundary(2.8)
                                                                           .propertyName(PROPERTY_NAME)
                                                                           .description("niskie")
                                                                           .build();
    public static final TrapezoidalMemGradeDto MEDIUM = TrapezoidalMemGradeDto.builder()
                                                                              .lowerBoundary(2.0)
                                                                              .lowerExtremum(2.5)
                                                                              .upperExtremum(3.5)
                                                                              .upperBoundary(4.5)
                                                                              .propertyName(PROPERTY_NAME)
                                                                              .description("średnie")
                                                                              .build();
    public static final TrapezoidalMemGradeDto HIGH = TrapezoidalMemGradeDto.builder()
                                                                            .lowerBoundary(3.5)
                                                                            .lowerExtremum(4)
                                                                            .upperExtremum(6)
                                                                            .upperBoundary(8)
                                                                            .propertyName(PROPERTY_NAME)
                                                                            .description("wysokie")
                                                                            .build();
    public static final TrapezoidalMemGradeDto VERY_HIGH = TrapezoidalMemGradeDto.builder()
                                                                                 .lowerBoundary(7)
                                                                                 .lowerExtremum(9)
                                                                                 .upperExtremum(11)
                                                                                 .upperBoundary(MAX_VALUE)
                                                                                 .propertyName(PROPERTY_NAME)
                                                                                 .description("bardzo wysokie")
                                                                                 .build();
}
