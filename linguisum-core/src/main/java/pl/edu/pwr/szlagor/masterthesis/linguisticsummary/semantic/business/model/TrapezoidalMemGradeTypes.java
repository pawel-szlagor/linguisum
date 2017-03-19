package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model;

/**
 * Created by Pawel on 2017-03-17.
 */
public enum TrapezoidalMemGradeTypes {
    COLD_WATER("cold water"),
    HOT_WATER("hot water"),
    DAY_PHASE("day phase"),
    ELECTRICITY("electricity"),
    HUMIDITY("humidity"),
    PRECIPITATION("precipitation"),
    PRESSURE("pressure"),
    SUNLIGHT("sunlight"),
    DES_TEMP("desiredTemp"),
    TEMP_OUT("tempOut"),
    WIND_SPEED("windSpeed");

    private String name;

    TrapezoidalMemGradeTypes(String name) {
        this.name = name;
    }
}
