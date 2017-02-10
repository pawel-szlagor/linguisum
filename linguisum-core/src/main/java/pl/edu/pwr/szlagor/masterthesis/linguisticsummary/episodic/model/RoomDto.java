package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;

/**
 * Created by Pawel on 2017-01-15.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Long id;
    private String name;
    private RoomType roomType;

}
