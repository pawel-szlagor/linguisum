package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.lang.Double.MAX_VALUE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.TEMP_OUT;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class TempOutLevels {
    private static final String NAME = TEMP_OUT.name();
    public static final TrapezoidalMemGradeDto ICY = TrapezoidalMemGradeDto.builder()
                                                                           .lowerBoundary(-Double.MAX_VALUE)
                                                                           .lowerExtremum(-15.0)
                                                                           .upperExtremum(-10.0)
                                                                           .upperBoundary(-8.0)
                                                                           .propertyName(NAME)
                                                                           .description("lodowata")
                                                                           .build();
    public static final TrapezoidalMemGradeDto FREEZING = TrapezoidalMemGradeDto.builder()
                                                                                .lowerBoundary(-10.0)
                                                                                .lowerExtremum(-6.0)
                                                                                .upperExtremum(-1.0)
                                                                                .upperBoundary(1.0)
                                                                                .propertyName(NAME)
                                                                                .description("mroźna")
                                                                                .build();
    public static final TrapezoidalMemGradeDto COLD = TrapezoidalMemGradeDto.builder()
                                                                            .lowerBoundary(-1.0)
                                                                            .lowerExtremum(0.0)
                                                                            .upperExtremum(3.0)
                                                                            .upperBoundary(6.0)
                                                                            .propertyName(NAME)
                                                                            .description("zimna")
                                                                            .build();
    public static final TrapezoidalMemGradeDto COOL = TrapezoidalMemGradeDto.builder()
                                                                            .lowerBoundary(3.0)
                                                                            .lowerExtremum(5.0)
                                                                            .upperExtremum(8.0)
                                                                            .upperBoundary(10.0)
                                                                            .propertyName(NAME)
                                                                            .description("chłodna")
                                                                            .build();
    public static final TrapezoidalMemGradeDto MODERATELY = TrapezoidalMemGradeDto.builder()
                                                                                  .lowerBoundary(8.0)
                                                                                  .lowerExtremum(12.0)
                                                                                  .upperExtremum(15.0)
                                                                                  .upperBoundary(16.0)
                                                                                  .propertyName(NAME)
                                                                                  .description("umiarkowana")
                                                                                  .build();
    public static final TrapezoidalMemGradeDto WARM = TrapezoidalMemGradeDto.builder()
                                                                            .lowerBoundary(14.0)
                                                                            .lowerExtremum(19.0)
                                                                            .upperExtremum(21.0)
                                                                            .upperBoundary(22.0)
                                                                            .propertyName(NAME)
                                                                            .description("ciepła")
                                                                            .build();
    public static final TrapezoidalMemGradeDto HOT = TrapezoidalMemGradeDto.builder()
                                                                           .lowerBoundary(20.0)
                                                                           .lowerExtremum(23.0)
                                                                           .upperExtremum(25.0)
                                                                           .upperBoundary(27.0)
                                                                           .propertyName(NAME)
                                                                           .description("gorąca")
                                                                           .build();
    public static final TrapezoidalMemGradeDto SWELTERING = TrapezoidalMemGradeDto.builder()
                                                                                  .lowerBoundary(25.0)
                                                                                  .lowerExtremum(29.0)
                                                                                  .upperExtremum(32.0)
                                                                                  .upperBoundary(36.0)
                                                                                  .propertyName(NAME)
                                                                                  .description("upalna")
                                                                                  .build();
    public static final TrapezoidalMemGradeDto HEAT = TrapezoidalMemGradeDto.builder()
                                                                            .lowerBoundary(28.0)
                                                                            .lowerExtremum(32.0)
                                                                            .upperExtremum(36.0)
                                                                            .upperBoundary(MAX_VALUE)
                                                                            .propertyName(NAME)
                                                                            .description("żarna")
                                                                            .build();
}
