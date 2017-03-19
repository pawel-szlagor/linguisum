package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by Pawel on 2017-01-16.
 */
@Embeddable
public class PersonState {

    @Indexed
    private Long userId;

    @Indexed
    private Long locationId;

    @java.beans.ConstructorProperties({ "userId", "locationId" })
    public PersonState(Long userId, Long locationId) {
        this.userId = userId;
        this.locationId = locationId;
    }

    public PersonState() {
    }

    public static PersonStateBuilder builder() {
        return new PersonStateBuilder();
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonState))
            return false;
        final PersonState other = (PersonState) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId))
            return false;
        final Object this$locationId = this.getLocationId();
        final Object other$locationId = other.getLocationId();
        if (this$locationId == null ? other$locationId != null : !this$locationId.equals(other$locationId))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $locationId = this.getLocationId();
        result = result * PRIME + ($locationId == null ? 43 : $locationId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof PersonState;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState(userId=" + this.getUserId() + ", locationId="
                + this.getLocationId() + ")";
    }

    public static class PersonStateBuilder {
        private Long userId;
        private Long locationId;

        PersonStateBuilder() {
        }

        public PersonState.PersonStateBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public PersonState.PersonStateBuilder locationId(Long locationId) {
            this.locationId = locationId;
            return this;
        }

        public PersonState build() {
            return new PersonState(userId, locationId);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState.PersonStateBuilder(userId=" + this.userId
                    + ", locationId=" + this.locationId + ")";
        }
    }
}
