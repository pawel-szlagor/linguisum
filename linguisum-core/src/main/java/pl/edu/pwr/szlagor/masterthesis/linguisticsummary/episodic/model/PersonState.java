package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * Created by Pawel on 2017-01-16.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersonState {

    private Long userId;

    private Long locationId;
}
