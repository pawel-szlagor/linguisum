package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Pawel on 2017-01-29.
 */
@ToString(of = "name")
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
