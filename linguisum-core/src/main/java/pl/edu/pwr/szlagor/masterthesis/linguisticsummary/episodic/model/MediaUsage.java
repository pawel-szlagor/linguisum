package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;

/**
 * Created by Pawel on 2017-01-16.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MediaUsage {
    private MediaType mediaType;
    private double usagePerMinute;
    private Long roomId;
}
