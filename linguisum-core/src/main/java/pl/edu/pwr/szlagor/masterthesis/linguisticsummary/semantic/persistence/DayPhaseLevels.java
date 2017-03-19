package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DAY_PHASE;

import java.time.LocalTime;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class DayPhaseLevels {
    private static final String PROPERTY_NAME = DAY_PHASE.name();
    public static final TrapezoidalMemGradeDto NIGHT = TrapezoidalMemGradeDto.builder()
                                                                             .lowerBoundary(LocalTime.MIDNIGHT.toSecondOfDay())
                                                                             .lowerExtremum(LocalTime.MIDNIGHT.toSecondOfDay())
                                                                             .upperExtremum(LocalTime.of(6, 0, 0).toSecondOfDay())
                                                                             .upperBoundary(LocalTime.of(7, 0, 0).toSecondOfDay())
                                                                             .propertyName(PROPERTY_NAME)
                                                                             .description("noc")
                                                                             .build();
    public static final TrapezoidalMemGradeDto MORNING = TrapezoidalMemGradeDto.builder()
                                                                               .lowerBoundary(LocalTime.of(6, 0, 0).toSecondOfDay())
                                                                               .lowerExtremum(LocalTime.of(6, 30, 0).toSecondOfDay())
                                                                               .upperExtremum(LocalTime.of(8, 0, 0).toSecondOfDay())
                                                                               .upperBoundary(LocalTime.of(9, 0, 0).toSecondOfDay())
                                                                               .propertyName(PROPERTY_NAME)
                                                                               .description("poranek")
                                                                               .build();
    public static final TrapezoidalMemGradeDto BEFORE_NOON = TrapezoidalMemGradeDto.builder()
                                                                                   .lowerBoundary(LocalTime.of(7, 30, 0).toSecondOfDay())
                                                                                   .lowerExtremum(LocalTime.of(8, 0, 0).toSecondOfDay())
                                                                                   .upperExtremum(LocalTime.of(12, 0, 0).toSecondOfDay())
                                                                                   .upperBoundary(LocalTime.of(13, 0, 0).toSecondOfDay())
                                                                                   .propertyName(PROPERTY_NAME)
                                                                                   .description("przedpołudnie")
                                                                                   .build();
    public static final TrapezoidalMemGradeDto AFTERNOON = TrapezoidalMemGradeDto.builder()
                                                                                 .lowerBoundary(LocalTime.of(11, 30, 0).toSecondOfDay())
                                                                                 .lowerExtremum(LocalTime.of(12, 0, 0).toSecondOfDay())
                                                                                 .upperExtremum(LocalTime.of(16, 30, 0).toSecondOfDay())
                                                                                 .upperBoundary(LocalTime.of(18, 0, 0).toSecondOfDay())
                                                                                 .propertyName(PROPERTY_NAME)
                                                                                 .description("popołudnie")
                                                                                 .build();
    public static final TrapezoidalMemGradeDto EVENING = TrapezoidalMemGradeDto.builder()
                                                                               .lowerBoundary(LocalTime.of(16, 0, 0).toSecondOfDay())
                                                                               .lowerExtremum(LocalTime.of(16, 30, 0).toSecondOfDay())
                                                                               .upperExtremum(LocalTime.of(21, 30, 0).toSecondOfDay())
                                                                               .upperBoundary(LocalTime.of(22, 30, 0).toSecondOfDay())
                                                                               .propertyName(PROPERTY_NAME)
                                                                               .description("wieczór")
                                                                               .build();
    public static final TrapezoidalMemGradeDto BEFORE_MIDNIGHT = TrapezoidalMemGradeDto.builder()
                                                                                       .lowerBoundary(
                                                                                               LocalTime.of(21, 30, 0).toSecondOfDay())
                                                                                       .lowerExtremum(
                                                                                               LocalTime.of(22, 30, 0).toSecondOfDay())
                                                                                       .upperExtremum(
                                                                                               LocalTime.of(23, 30, 0).toSecondOfDay())
                                                                                       .upperBoundary(LocalTime.MAX.toSecondOfDay())
                                                                                       .propertyName(PROPERTY_NAME)
                                                                                       .description("przednoc")
                                                                                       .build();
}
