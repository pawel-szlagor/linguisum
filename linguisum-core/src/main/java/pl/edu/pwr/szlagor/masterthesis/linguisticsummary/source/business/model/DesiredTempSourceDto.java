package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class DesiredTempSourceDto {

    private Long id;
    private LocalDateTime observationTime;
    private PersonSourceDto user;
    private double desiredTemp;
    private RoomSourceDto location;
}
