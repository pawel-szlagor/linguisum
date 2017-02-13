package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;

import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-01-31.
 */
@EqualsAndHashCode
@ToString(of = {"mediaType", "location", "usagePerMinute"})
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaUsageSourceDto implements Cloneable, ObservationTimeAware {
    private Long id;
    private LocalDateTime observationTime;
    private MediaType mediaType;
    private double usagePerMinute;
    private RoomSourceDto location;
}
