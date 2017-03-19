package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

/**
 * Created by Pawel on 2017-01-16.
 */
@Embeddable
public class RoomStateDto {

    private RoomDto room;
    private PersonDto person;
    private double desiredTemp;

    @java.beans.ConstructorProperties({ "room", "person", "desiredTemp" })
    public RoomStateDto(RoomDto room, PersonDto person, double desiredTemp) {
        this.room = room;
        this.person = person;
        this.desiredTemp = desiredTemp;
    }

    public RoomStateDto() {
    }

    public RoomDto getRoom() {
        return this.room;
    }

    public PersonDto getPerson() {
        return this.person;
    }

    public double getDesiredTemp() {
        return this.desiredTemp;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public void setDesiredTemp(double desiredTemp) {
        this.desiredTemp = desiredTemp;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RoomStateDto))
            return false;
        final RoomStateDto other = (RoomStateDto) o;
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
        return other instanceof RoomStateDto;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomStateDto(room=" + this.getRoom() + ", person="
                + this.getPerson() + ", desiredTemp=" + this.getDesiredTemp() + ")";
    }
}
