package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums;

import java.util.Arrays;

import lombok.Getter;

/**
 * Created by Pawel on 2017-01-17.
 */
@Getter
public enum WeatherEvent {
    FOG("mgła"),
    RAIN("opady deszczu"),
    SNOW("opady śniegu"),
    HAIL("grad"),
    THUNDERSTORM("burza");

    private String key;

    WeatherEvent(String key) {
        this.key = key;
    }

    public static WeatherEvent getByKey(String key) {
        return Arrays.stream(values()).filter(l -> l.key.equals(key)).findFirst().get();
    }
}
