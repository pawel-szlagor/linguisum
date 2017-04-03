package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy;

import static org.apache.commons.collections.CollectionUtils.intersection;

import java.util.Map;
import java.util.Set;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.index.Indexed;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.TrapezoidalMemGrade;

/**
 * Created by Pawel on 2017-01-16.
 */
@Entity
public class FRoomState {
    @Indexed
    private Room room;
    @Indexed
    private Person person;
    private Set<TrapezoidalMemGrade> fDesiredTemp;
    private Map<TrapezoidalMemGrade, Double> fMembershipProb;

    @java.beans.ConstructorProperties({ "room", "person", "fDesiredTemp", "fMembershipProb" })
    public FRoomState(Room room, Person person, Set<TrapezoidalMemGrade> fDesiredTemp, Map<TrapezoidalMemGrade, Double> fMembershipProb) {
        this.room = room;
        this.person = person;
        this.fDesiredTemp = fDesiredTemp;
        this.fMembershipProb = fMembershipProb;
    }

    public FRoomState() {
    }

    public static FRoomStateBuilder builder() {
        return new FRoomStateBuilder();
    }

    public Room getRoom() {
        return this.room;
    }

    public Person getPerson() {
        return this.person;
    }

    public Set<TrapezoidalMemGrade> getFDesiredTemp() {
        return this.fDesiredTemp;
    }

    public Map<TrapezoidalMemGrade, Double> getFMembershipProb() {
        return this.fMembershipProb;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setFDesiredTemp(Set<TrapezoidalMemGrade> fDesiredTemp) {
        this.fDesiredTemp = fDesiredTemp;
    }

    public void setFMembershipProb(Map<TrapezoidalMemGrade, Double> fMembershipProb) {
        this.fMembershipProb = fMembershipProb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FRoomState that = (FRoomState) o;

        if (room != null ? !room.equals(that.room) : that.room != null)
            return false;
        if (person != null ? !person.equals(that.person) : that.person != null)
            return false;
        return fDesiredTemp != null ? !intersection(fDesiredTemp, that.fDesiredTemp).isEmpty() : that.fDesiredTemp == null;
    }

    @Override
    public int hashCode() {
        int result = room != null ? room.hashCode() : 0;
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (fDesiredTemp != null ? fDesiredTemp.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "FRoomState(room=" + this.getRoom()
                + ", person=" + this.getPerson() + ", fDesiredTemp=" + this.getFDesiredTemp() + ", fMembershipProb="
                + this.getFMembershipProb() + ")";
    }

    public static class FRoomStateBuilder {
        private Room room;
        private Person person;
        private Set<TrapezoidalMemGrade> fDesiredTemp;
        private Map<TrapezoidalMemGrade, Double> fMembershipProb;

        FRoomStateBuilder() {
        }

        public FRoomState.FRoomStateBuilder room(Room room) {
            this.room = room;
            return this;
        }

        public FRoomState.FRoomStateBuilder person(Person person) {
            this.person = person;
            return this;
        }

        public FRoomState.FRoomStateBuilder fDesiredTemp(Set<TrapezoidalMemGrade> fDesiredTemp) {
            this.fDesiredTemp = fDesiredTemp;
            return this;
        }

        public FRoomState.FRoomStateBuilder fMembershipProb(Map<TrapezoidalMemGrade, Double> fMembershipProb) {
            this.fMembershipProb = fMembershipProb;
            return this;
        }

        public FRoomState build() {
            return new FRoomState(room, person, fDesiredTemp, fMembershipProb);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.FRoomState.FRoomStateBuilder(room="
                    + this.room + ", person=" + this.person + ", fDesiredTemp=" + this.fDesiredTemp + ", fMembershipProb="
                    + this.fMembershipProb + ")";
        }
    }
}
