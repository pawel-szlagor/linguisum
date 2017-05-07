package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Pawel on 2017-05-05.
 */
@AllArgsConstructor
@Builder
@Data
public class QuantificatorLinguisticDto {
    private Long id;
    private String label;
    private double lowerBoundary;
    private double upperBoundary;
    private String description;
}
