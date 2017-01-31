package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.enums.WeatherEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 2017-01-29.
 */
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
