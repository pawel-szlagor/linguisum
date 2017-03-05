package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;

/**
 * Created by Pawel on 2017-01-29.
 */
@ToString(of = "name")
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
