package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums;

/**
 * Created by Pawel on 2017-01-16.
 */
public enum MediaType {
    HOT_WATER("hot water", "dm3"),
    COLD_WATER("cold water", "dm3"),
    ELECTRICITY("electricity", "kWh"),
    GAS("gas", "m3)");

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
