package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by Pawel on 2017-01-16.
 */
@Entity
@Embeddable
public class RoomState {
    @Indexed
    private Room room;
    @Indexed
    private Person person;
    @Indexed
    private double desiredTemp;

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

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RoomState))
            return false;
        final RoomState other = (RoomState) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$room = this.getRoom();
        final Object other$room = other.getRoom();
        if (this$room == null ? other$room != null : !this$room.equals(other$room))
            return false;
        final Object this$person = this.getPerson();
        final Object other$person = other.getPerson();
        if (this$person == null ? other$person != null : !this$person.equals(other$person))
            return false;
        if (Double.compare(this.getDesiredTemp(), other.getDesiredTemp()) != 0)
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $room = this.getRoom();
        result = result * PRIME + ($room == null ? 43 : $room.hashCode());
        final Object $person = this.getPerson();
        result = result * PRIME + ($person == null ? 43 : $person.hashCode());
        final long $desiredTemp = Double.doubleToLongBits(this.getDesiredTemp());
        result = result * PRIME + (int) ($desiredTemp >>> 32 ^ $desiredTemp);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoomState;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomState(room=" + this.getRoom() + ", person="
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
