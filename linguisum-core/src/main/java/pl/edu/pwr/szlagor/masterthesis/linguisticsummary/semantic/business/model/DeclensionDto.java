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
public class DeclensionDto {
    private Long id;
    private String nominative;
    private String genitive;
    private String dative;
    private String accusative;
    private String instrumental;
    private String locative;
}
