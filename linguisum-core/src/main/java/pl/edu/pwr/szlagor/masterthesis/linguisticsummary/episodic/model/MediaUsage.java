package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;

/**
 * Created by Pawel on 2017-01-16.
 */
@Embeddable
@Entity
public class MediaUsage {
    private MediaType mediaType;
    @Indexed
    private double usagePerMinute;
    @DBRef(db = "room")
    private Room location;

    @java.beans.ConstructorProperties({ "mediaType", "usagePerMinute", "location" })
    public MediaUsage(MediaType mediaType, double usagePerMinute, Room location) {
        this.mediaType = mediaType;
        this.usagePerMinute = usagePerMinute;
        this.location = location;
    }

    public MediaUsage() {
    }

    public static MediaUsageBuilder builder() {
        return new MediaUsageBuilder();
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public double getUsagePerMinute() {
        return this.usagePerMinute;
    }

    public Room getLocation() {
        return this.location;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setUsagePerMinute(double usagePerMinute) {
        this.usagePerMinute = usagePerMinute;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MediaUsage that = (MediaUsage) o;

        if (mediaType != that.mediaType)
            return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }

    @Override
    public int hashCode() {
        int result = mediaType != null ? mediaType.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof MediaUsage;
    }

    public String toString() {
		return "MediaUsage(mediaType=" + this.getMediaType()
                + ", usagePerMinute=" + this.getUsagePerMinute() + ", location=" + this.getLocation() + ")";
    }

    public static class MediaUsageBuilder {
        private MediaType mediaType;
        private double usagePerMinute;
        private Room location;

        MediaUsageBuilder() {
        }

        public MediaUsage.MediaUsageBuilder mediaType(MediaType mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public MediaUsage.MediaUsageBuilder usagePerMinute(double usagePerMinute) {
            this.usagePerMinute = usagePerMinute;
            return this;
        }

        public MediaUsage.MediaUsageBuilder location(Room location) {
            this.location = location;
            return this;
        }

        public MediaUsage build() {
            return new MediaUsage(mediaType, usagePerMinute, location);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.MediaUsage.MediaUsageBuilder(mediaType="
                    + this.mediaType + ", usagePerMinute=" + this.usagePerMinute + ", location=" + this.location + ")";
        }
    }
}
