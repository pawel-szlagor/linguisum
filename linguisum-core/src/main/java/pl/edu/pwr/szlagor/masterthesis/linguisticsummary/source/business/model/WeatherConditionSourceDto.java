package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;

/**
 * Created by Pawel on 2017-01-29.
 */
@EqualsAndHashCode
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherConditionSourceDto {

    private LocalDateTime observationTime = LocalDateTime.now();
    private double tempOut;
    private double windchill;
    private double humidity;
    private int pressure;
    private double windSpeed;
    private double precipitation;
    @Singular
    private List<WeatherEvent> weatherEvents = new ArrayList<>();
    private double sunlightEmission;
}
