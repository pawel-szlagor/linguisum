package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums;

import java.util.Arrays;

/**
 * Created by Pawel on 2017-01-17.
 */
public enum WeatherEvent {
    FOG("Fog"),
    RAIN("Rain"),
    SNOW("Snow"),
    HAIL("Hail"),
    THUNDERSTORM("Thunderstorm");

    private String key;

    WeatherEvent(String key) {
        this.key = key;
    }

    public static WeatherEvent getByKey(String key) {
        return Arrays.stream(values()).filter(l -> l.key.equals(key)).findFirst().get();
    }
}
