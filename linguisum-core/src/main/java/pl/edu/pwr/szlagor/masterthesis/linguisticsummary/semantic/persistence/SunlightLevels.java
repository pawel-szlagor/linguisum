package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.SUNLIGHT;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class SunlightLevels {
    private static final String PROPERTY_NAME = SUNLIGHT.name();
    public static final TrapezoidalMemGradeDto NONE = TrapezoidalMemGradeDto.builder().lowerBoundary(-Double.MAX_VALUE).lowerExtremum(5.0).upperExtremum(10.0).upperBoundary(15.0).propertyName(PROPERTY_NAME).description("brak słońca").build();
    public static final TrapezoidalMemGradeDto LOW = TrapezoidalMemGradeDto.builder().lowerBoundary(12.0).lowerExtremum(30.0).upperExtremum(50.0).upperBoundary(100.0).propertyName(PROPERTY_NAME).description("niskie nasłonecznienie").build();
    public static final TrapezoidalMemGradeDto MEDIUM = TrapezoidalMemGradeDto.builder().lowerBoundary(75.0).lowerExtremum(150.0).upperExtremum(250.0).upperBoundary(500.0).propertyName(PROPERTY_NAME).description("średnie nasłonecznienie").build();
    public static final TrapezoidalMemGradeDto HIGH = TrapezoidalMemGradeDto.builder().lowerBoundary(300.0).lowerExtremum(600.0).upperExtremum(2000.0).upperBoundary(MAX_VALUE).propertyName(PROPERTY_NAME).description("wysokie nasłonecznienie").build();
}
