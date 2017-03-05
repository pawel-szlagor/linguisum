package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;

/**
 * Created by Pawel on 2017-01-31.
 */
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
