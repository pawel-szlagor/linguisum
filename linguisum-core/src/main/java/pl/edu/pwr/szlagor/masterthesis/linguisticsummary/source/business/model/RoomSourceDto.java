package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.enums.RoomType;

/**
 * Created by Pawel on 2017-01-29.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomSourceDto {

    private Long id;
    private String name;
    private RoomType type;
}
