package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-01-29.
 */
@ToString(of = {"location", "desiredTemp", "user"})
@EqualsAndHashCode
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesiredTempSourceDto implements ObservationTimeAware{

    private Long id;
    private LocalDateTime observationTime;
    private PersonSourceDto user;
    private double desiredTemp;
    private RoomSourceDto location;
}
