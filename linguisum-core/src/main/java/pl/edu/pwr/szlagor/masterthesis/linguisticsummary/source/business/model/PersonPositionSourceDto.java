package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-01-29.
 */
@ToString(of = {"user", "location"})
@EqualsAndHashCode
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonPositionSourceDto implements ObservationTimeAware{

    private LocalDateTime observationTime;
    private PersonSourceDto user;
    private RoomSourceDto location;
}
