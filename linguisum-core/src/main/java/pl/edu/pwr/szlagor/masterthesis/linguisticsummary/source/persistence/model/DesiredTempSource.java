package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Pawel on 2017-01-22.
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DESIRED_TEMP")
@Entity
public class DesiredTempSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private PersonSource user;

    private float desiredTemp;

    @ManyToOne
    @JoinColumn(name = "ID_LOCATION")
    private RoomSource location;
}
