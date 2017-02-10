package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-02-05.
 */
@EqualsAndHashCode
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLabelSourceDto {
    private long id;
    private String label;
    private LocalDateTime observationTime;
}
