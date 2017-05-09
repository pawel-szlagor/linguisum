package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums;

/**
 * Created by Pawel on 2017-01-16.
 */
public enum MediaType {
    HOT_WATER("gorąca woda", "dm3"),
    COLD_WATER("zimna woda", "dm3"),
    ELECTRICITY("elektryczność", "kWh"),
    GAS("gaz", "m3)");

    private String name;
    private String unit;

    MediaType(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
