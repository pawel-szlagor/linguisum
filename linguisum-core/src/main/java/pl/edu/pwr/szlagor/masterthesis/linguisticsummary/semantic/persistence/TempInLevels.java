package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DES_TEMP;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class TempInLevels {
    private static final String PROPERTY_NAME = DES_TEMP.name();
    public static final TrapezoidalMemGradeDto ICY = TrapezoidalMemGradeDto.builder().lowerBoundary(-Double.MAX_VALUE).lowerExtremum(14.0).upperExtremum(15.0).upperBoundary(16.0).propertyName(PROPERTY_NAME).description("lodowata").build();
    public static final TrapezoidalMemGradeDto FREEZING = TrapezoidalMemGradeDto.builder().lowerBoundary(15.0).lowerExtremum(16.0).upperExtremum(17.5).upperBoundary(18.5).propertyName(PROPERTY_NAME).description("mroźna").build();
    public static final TrapezoidalMemGradeDto COLD = TrapezoidalMemGradeDto.builder().lowerBoundary(16.5).lowerExtremum(17.0).upperExtremum(19.0).upperBoundary(20.0).propertyName(PROPERTY_NAME).description("zimna").build();
    public static final TrapezoidalMemGradeDto MODERATELY = TrapezoidalMemGradeDto.builder().lowerBoundary(18.0).lowerExtremum(19.0).upperExtremum(22.0).upperBoundary(23.0).propertyName(PROPERTY_NAME).description("umiarkowana").build();
    public static final TrapezoidalMemGradeDto WARM = TrapezoidalMemGradeDto.builder().lowerBoundary(20.0).lowerExtremum(22.0).upperExtremum(23.5).upperBoundary(25.0).propertyName(PROPERTY_NAME).description("ciepła").build();
    public static final TrapezoidalMemGradeDto HOT = TrapezoidalMemGradeDto.builder().lowerBoundary(23.0).lowerExtremum(24.0).upperExtremum(27.0).upperBoundary(MAX_VALUE).propertyName(PROPERTY_NAME).description("gorąca").build();

}
