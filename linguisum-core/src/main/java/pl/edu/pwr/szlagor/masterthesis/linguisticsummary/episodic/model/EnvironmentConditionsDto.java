package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;

/**
 * Created by Pawel on 2017-01-15.
 */
@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnvironmentConditionsDto {

    private double tempOut;
    private double windchill;
    private double humidity;
    private int pressure;
    private double windSpeed;
    private double precipitation;
    @Singular
    private List<WeatherEvent> weatherEvents;
    private double sunlightEmission;

}
