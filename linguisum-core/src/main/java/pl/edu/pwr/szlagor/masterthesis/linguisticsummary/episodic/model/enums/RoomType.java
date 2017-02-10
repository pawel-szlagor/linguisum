package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums;

import lombok.Getter;

/**
 * Created by Pawel on 2017-01-16.
 */
@Getter
public enum RoomType {
    LIVING_ROOM("Living room"), KITCHEN("Kitchen"), BATHROOM("Bathroom"), TOILETTE("Toilette"), SLEEPING_ROOM("Sleeping room"), DINING_ROOM("Dining room"), HALL("Hall");

    private String name;

    RoomType(String name) {
        this.name = name;
    }
}
