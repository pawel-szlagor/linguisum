package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.FallType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;

/**
 * Created by Pawel on 2017-01-15.
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class EnvironmentConditions {

    private double tempOutside;
    private double windChill;
    private int humidity;
    private int pressure;
    private double windSpeed;
    private double fall;
    private FallType fallType;
    private WeatherEvent weatherEvent;
    private double sunlight;

}
