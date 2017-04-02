package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy;

import static org.apache.commons.collections.CollectionUtils.intersection;

import java.util.Map;
import java.util.Set;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.index.Indexed;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.TrapezoidalMemGrade;

/**
 * Created by Pawel on 2017-01-16.
 */
@Entity
public class FMediaUsage {
    private MediaType mediaType;
    private Set<TrapezoidalMemGrade> fUsagePerMinute;
    private Map<TrapezoidalMemGrade, Double> fMembershipProb;
    @Indexed
    private Room location;

    @java.beans.ConstructorProperties({ "mediaType", "fUsagePerMinute", "fMembershipProb", "location" })
    public FMediaUsage(MediaType mediaType,
            Set<TrapezoidalMemGrade> fUsagePerMinute,
            Map<TrapezoidalMemGrade, Double> fMembershipProb,
            Room location) {
        this.mediaType = mediaType;
        this.fUsagePerMinute = fUsagePerMinute;
        this.fMembershipProb = fMembershipProb;
        this.location = location;
    }

    public FMediaUsage() {
    }

    public static FMediaUsageBuilder builder() {
        return new FMediaUsageBuilder();
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public Set<TrapezoidalMemGrade> getFUsagePerMinute() {
        return this.fUsagePerMinute;
    }

    public Map<TrapezoidalMemGrade, Double> getFMembershipProb() {
        return this.fMembershipProb;
    }

    public Room getLocation() {
        return this.location;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setFUsagePerMinute(Set<TrapezoidalMemGrade> fUsagePerMinute) {
        this.fUsagePerMinute = fUsagePerMinute;
    }

    public void setFMembershipProb(Map<TrapezoidalMemGrade, Double> fMembershipProb) {
        this.fMembershipProb = fMembershipProb;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public static class FMediaUsageBuilder {
        private MediaType mediaType;
        private Set<TrapezoidalMemGrade> fUsagePerMinute;
        private Map<TrapezoidalMemGrade, Double> fMembershipProb;
        private Room location;

        FMediaUsageBuilder() {
        }

        public FMediaUsage.FMediaUsageBuilder mediaType(MediaType mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public FMediaUsage.FMediaUsageBuilder fUsagePerMinute(Set<TrapezoidalMemGrade> fUsagePerMinute) {
            this.fUsagePerMinute = fUsagePerMinute;
            return this;
        }

        public FMediaUsage.FMediaUsageBuilder fMembershipProb(Map<TrapezoidalMemGrade, Double> fMembershipProb) {
            this.fMembershipProb = fMembershipProb;
            return this;
        }

        public FMediaUsage.FMediaUsageBuilder location(Room location) {
            this.location = location;
            return this;
        }

        public FMediaUsage build() {
            return new FMediaUsage(mediaType, fUsagePerMinute, fMembershipProb, location);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            FMediaUsageBuilder that = (FMediaUsageBuilder) o;

            if (mediaType != that.mediaType)
                return false;
            if (fUsagePerMinute != null ? !intersection(fUsagePerMinute, that.fUsagePerMinute).isEmpty() : that.fUsagePerMinute != null)
                return false;
            return location != null ? location.equals(that.location) : that.location == null;
        }

        @Override
        public int hashCode() {
            int result = mediaType != null ? mediaType.hashCode() : 0;
            result = 31 * result + (fUsagePerMinute != null ? fUsagePerMinute.hashCode() : 0);
            result = 31 * result + (location != null ? location.hashCode() : 0);
            return result;
        }

        public String toString() {
            return "FMediaUsage.FMediaUsageBuilder(mediaType="
                    + this.mediaType + ", fUsagePerMinute=" + this.fUsagePerMinute + ", fMembershipProb=" + this.fMembershipProb
                    + ", location=" + this.location + ")";
        }
    }
}
