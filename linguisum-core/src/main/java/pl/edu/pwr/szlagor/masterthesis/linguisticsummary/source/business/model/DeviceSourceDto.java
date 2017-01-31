package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.RoomSource;

/**
 * Created by Pawel on 2017-01-29.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceSourceDto {

    private Long id;
    private PersonSource user;
    private float desiredTemp;
    private RoomSource location;
}
