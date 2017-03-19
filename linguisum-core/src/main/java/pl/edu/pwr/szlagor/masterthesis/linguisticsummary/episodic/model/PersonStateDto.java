package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

/**
 * Created by Pawel on 2017-01-16.
 */
public class PersonStateDto {

    private PersonDto person;

    private RoomDto room;

    @java.beans.ConstructorProperties({ "person", "room" })
    public PersonStateDto(PersonDto person, RoomDto room) {
        this.person = person;
        this.room = room;
    }

    public PersonStateDto() {
    }

    public PersonDto getPerson() {
        return this.person;
    }

    public RoomDto getRoom() {
        return this.room;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonStateDto))
            return false;
        final PersonStateDto other = (PersonStateDto) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$person = this.getPerson();
        final Object other$person = other.getPerson();
        if (this$person == null ? other$person != null : !this$person.equals(other$person))
            return false;
        final Object this$room = this.getRoom();
        final Object other$room = other.getRoom();
        if (this$room == null ? other$room != null : !this$room.equals(other$room))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $person = this.getPerson();
        result = result * PRIME + ($person == null ? 43 : $person.hashCode());
        final Object $room = this.getRoom();
        result = result * PRIME + ($room == null ? 43 : $room.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof PersonStateDto;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonStateDto(person=" + this.getPerson() + ", room="
                + this.getRoom() + ")";
    }
}
