package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.QuantificatorLinguisticDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public class QuantificatorLinguisticData {
    public static final QuantificatorLinguisticDto NEVER = QuantificatorLinguisticDto.builder()
                                                                                     .lowerBoundary(0.00)
                                                                                     .upperBoundary(0.00)
                                                                                     .label("nigdy")
                                                                                     .build();
    public static final QuantificatorLinguisticDto VERY_SELDOM = QuantificatorLinguisticDto.builder()
                                                                                           .lowerBoundary(0.00)
                                                                                           .upperBoundary(0.15)
                                                                                           .label("bardzo rzadko")
                                                                                           .build();
    public static final QuantificatorLinguisticDto SELDOM = QuantificatorLinguisticDto.builder()
                                                                                      .lowerBoundary(0.15)
                                                                                      .upperBoundary(0.35)
                                                                                      .label("rzadko")
                                                                                      .build();
    public static final QuantificatorLinguisticDto USUALLY = QuantificatorLinguisticDto.builder()
                                                                                       .lowerBoundary(0.35)
                                                                                       .upperBoundary(0.65)
                                                                                       .label("zazwyczaj")
                                                                                       .build();
    public static final QuantificatorLinguisticDto OFTEN = QuantificatorLinguisticDto.builder()
                                                                                     .lowerBoundary(0.65)
                                                                                     .upperBoundary(0.8)
                                                                                     .label("często")
                                                                                     .build();
    public static final QuantificatorLinguisticDto VERY_OFTEN = QuantificatorLinguisticDto.builder()
                                                                                          .lowerBoundary(0.8)
                                                                                          .upperBoundary(1.0)
                                                                                          .label("bardzo często")
                                                                                          .build();
    public static final QuantificatorLinguisticDto ALWAYS = QuantificatorLinguisticDto.builder()
                                                                                      .lowerBoundary(1.0)
                                                                                      .upperBoundary(1.0)
                                                                                      .label("zawsze")
                                                                                      .build();
}
