package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-02-05.
 */
@EqualsAndHashCode
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLabelSourceDto implements ObservationTimeAware{
    private long id;
    private String label;
    private LocalDateTime observationTime;
}
