package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.WIND_SPEED;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class WindSpeedLevels {
    private static final String PROPERTY_NAME = WIND_SPEED.name();
    public static final TrapezoidalMemGradeDto PEACE = TrapezoidalMemGradeDto.builder().lowerBoundary(-Double.MAX_VALUE).lowerExtremum(0).upperExtremum(1).upperBoundary(2).propertyName(PROPERTY_NAME).description("cisza").build();
    public static final TrapezoidalMemGradeDto MILD_WIND = TrapezoidalMemGradeDto.builder().lowerBoundary(1.0).lowerExtremum(1.5).upperExtremum(2.5).upperBoundary(3).propertyName(PROPERTY_NAME).description("delikatny wiatr").build();
    public static final TrapezoidalMemGradeDto STRONG_WIND = TrapezoidalMemGradeDto.builder().lowerBoundary(2.5).lowerExtremum(3.5).upperExtremum(5).upperBoundary(6.0).propertyName(PROPERTY_NAME).description("silny wiatr").build();
    public static final TrapezoidalMemGradeDto GALE = TrapezoidalMemGradeDto.builder().lowerBoundary(5.0).lowerExtremum(7).upperExtremum(10).upperBoundary(MAX_VALUE).propertyName(PROPERTY_NAME).description("wichura").build();
}
