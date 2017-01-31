package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.enums.RoomType;

import javax.persistence.*;

/**
 * Created by Pawel on 2017-01-22.
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "LOCATION")
@Entity
public class RoomSource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_LOCATION")
    private Long id;
    private String name;
    private RoomType type;
}
