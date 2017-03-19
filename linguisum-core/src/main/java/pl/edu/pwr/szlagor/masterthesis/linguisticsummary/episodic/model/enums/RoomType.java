package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums;

/**
 * Created by Pawel on 2017-01-16.
 */
public enum RoomType {
    LIVING_ROOM("Living room"), KITCHEN("Kitchen"), BATHROOM("Bathroom"), TOILETTE("Toilette"), SLEEPING_ROOM("Sleeping room"), DINING_ROOM("Dining room"), HALL("Hall");

    private String name;

    RoomType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
