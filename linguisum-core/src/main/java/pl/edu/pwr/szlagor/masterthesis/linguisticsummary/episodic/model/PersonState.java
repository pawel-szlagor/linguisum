package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by Pawel on 2017-01-16.
 */
@Embeddable
public class PersonState {

    @DBRef
    private Person user;

    @Indexed
    @DBRef
    private Room location;

    @java.beans.ConstructorProperties({ "user", "location" })
    public PersonState(Person user, Room location) {
        this.user = user;
        this.location = location;
    }

    public PersonState() {
    }

    public static PersonStateBuilder builder() {
        return new PersonStateBuilder();
    }

    public Person getUser() {
        return this.user;
    }

    public Room getLocation() {
        return this.location;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public String toString() {
		return "PersonState(user=" + this.getUser() + ", location="
                + this.getLocation() + ")";
    }

    public static class PersonStateBuilder {
        private Person user;
        private Room location;

        PersonStateBuilder() {
        }

        public PersonState.PersonStateBuilder user(Person user) {
            this.user = user;
            return this;
        }

        public PersonState.PersonStateBuilder location(Room location) {
            this.location = location;
            return this;
        }

        public PersonState build() {
            return new PersonState(user, location);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState.PersonStateBuilder(user=" + this.user
                    + ", location=" + this.location + ")";
        }
    }
}
