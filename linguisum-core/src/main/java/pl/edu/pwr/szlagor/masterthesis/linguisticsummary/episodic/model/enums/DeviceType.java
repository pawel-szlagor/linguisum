package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums;

import lombok.Getter;

/**
 * Created by Pawel on 2017-01-16.
 */
@Getter
public enum DeviceType {
    WASH_MACHINE("Wash machine"), SHOWER("Shower"), OVEN("Oven"), MICROWAVE("Microwave"), ELECTRIC_KETTEL("Electric kettel"), PC("PC"), LAPTOP("Laptop");

    private String name;

    DeviceType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
