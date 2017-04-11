package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

/**
 * Created by Pawel on 2017-03-18.
 */
public enum CategoryPredicateTypes {
    DAY_PHASE("day phase"),
    HUMIDITY("humidity"),
    PRECIPITATION("precipitation"),
    PRECIPITATION_TYPE("precipitation_type"),
    PRESSURE("pressure"),
    SUNLIGHT("sunlight"),
    TEMP_OUT("tempOut"),
    WIND_SPEED("windSpeed"),
    DEVICE_STATE("device_state"),
    MEDIA_USAGE("media_usage"),
    PERSON_STATE("person_state"),
    ROOM_STATE("room_state");

    private String name;

    CategoryPredicateTypes(String name) {
        this.name = name;
    }
}
