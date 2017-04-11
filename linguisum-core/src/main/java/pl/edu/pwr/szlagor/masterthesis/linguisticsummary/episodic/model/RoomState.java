package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by Pawel on 2017-01-16.
 */
@Entity
@Embeddable
public class RoomState {
    @DBRef
    private Room room;
    @DBRef
    private Person person;
    @Indexed
    private Double desiredTemp;

    @java.beans.ConstructorProperties({ "room", "person", "desiredTemp" })
    public RoomState(Room room, Person person, double desiredTemp) {
        this.room = room;
        this.person = person;
        this.desiredTemp = desiredTemp;
    }

    public RoomState() {
    }

    public static RoomStateBuilder builder() {
        return new RoomStateBuilder();
    }

    public Room getRoom() {
        return this.room;
    }

    public Person getPerson() {
        return this.person;
    }

    public double getDesiredTemp() {
        return this.desiredTemp;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setDesiredTemp(double desiredTemp) {
        this.desiredTemp = desiredTemp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RoomState roomState = (RoomState) o;

        if (room != null ? !room.equals(roomState.room) : roomState.room != null)
            return false;
        return person != null ? person.equals(roomState.person) : roomState.person == null;
    }

    @Override
    public int hashCode() {
        int result = room != null ? room.hashCode() : 0;
        result = 31 * result + (person != null ? person.hashCode() : 0);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoomState;
    }

    public String toString() {
		return "RoomState(room=" + this.getRoom() + ", person="
                + this.getPerson() + ", desiredTemp=" + this.getDesiredTemp() + ")";
    }

    public static class RoomStateBuilder {
        private Room room;
        private Person person;
        private double desiredTemp;

        RoomStateBuilder() {
        }

        public RoomState.RoomStateBuilder room(Room room) {
            this.room = room;
            return this;
        }

        public RoomState.RoomStateBuilder person(Person person) {
            this.person = person;
            return this;
        }

        public RoomState.RoomStateBuilder desiredTemp(double desiredTemp) {
            this.desiredTemp = desiredTemp;
            return this;
        }

        public RoomState build() {
            return new RoomState(room, person, desiredTemp);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomState.RoomStateBuilder(room=" + this.room
                    + ", person=" + this.person + ", desiredTemp=" + this.desiredTemp + ")";
        }
    }
}
