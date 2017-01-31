package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.*;
import org.dozer.Mapping;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.enums.WeatherEvent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 2017-01-22.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WEATHER")
@Entity
public class WeatherConditionSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Mapping
    private LocalDateTime observationTime;
    private double tempOut;
    private double windchill;
    private double humidity;
    private int pressure;
    private double windSpeed;
    private double precipitation;
    @Singular
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<WeatherEvent> weatherEvents = new ArrayList<>();
    @Column(name = "SUNLIGHT")
    private double sunlightEmission;

}
