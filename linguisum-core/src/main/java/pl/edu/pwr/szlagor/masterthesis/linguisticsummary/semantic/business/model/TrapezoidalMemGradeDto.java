package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.membershipGradeFunctions.MemGrade;

/**
 * Created by Pawel on 2017-03-12.
 */
@AllArgsConstructor
@Builder
@Data
public class TrapezoidalMemGradeDto implements MemGrade {
    private Long id;
    private String propertyName;
    private double lowerBoundary;
    private double lowerExtremum;
    private double upperExtremum;
    private double upperBoundary;
    private String description;

    public double calculateMembershipGrade(double value) {
        if (value < lowerBoundary || value > upperBoundary) {
            return 0;
        } else if (value < lowerExtremum) {
            return (value - lowerBoundary) / (lowerExtremum - lowerBoundary);
        } else if (value < upperExtremum) {
            return 1;
        } else {
            return (upperBoundary - value) / (upperBoundary - upperExtremum);
        }
    }

    public boolean isBelonging(double value) {
        return value > lowerBoundary && value < upperBoundary;
    }
}
