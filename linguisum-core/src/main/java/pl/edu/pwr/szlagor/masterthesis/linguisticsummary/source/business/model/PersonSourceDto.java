package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;

/**
 * Created by Pawel on 2017-01-29.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonSourceDto {

    private Long id;
    private String name;
    private String mail;
}
