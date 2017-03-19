package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;

/**
 * Created by Pawel on 2017-01-16.
 */
public class MediaUsageDto {
    private MediaType mediaType;
    private double usagePerMinute;
    private RoomDto location;

    @java.beans.ConstructorProperties({ "mediaType", "usagePerMinute", "location" })
    public MediaUsageDto(MediaType mediaType, double usagePerMinute, RoomDto location) {
        this.mediaType = mediaType;
        this.usagePerMinute = usagePerMinute;
        this.location = location;
    }

    public MediaUsageDto() {
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public double getUsagePerMinute() {
        return this.usagePerMinute;
    }

    public RoomDto getLocation() {
        return this.location;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setUsagePerMinute(double usagePerMinute) {
        this.usagePerMinute = usagePerMinute;
    }

    public void setLocation(RoomDto location) {
        this.location = location;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MediaUsageDto))
            return false;
        final MediaUsageDto other = (MediaUsageDto) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$mediaType = this.getMediaType();
        final Object other$mediaType = other.getMediaType();
        if (this$mediaType == null ? other$mediaType != null : !this$mediaType.equals(other$mediaType))
            return false;
        if (Double.compare(this.getUsagePerMinute(), other.getUsagePerMinute()) != 0)
            return false;
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $mediaType = this.getMediaType();
        result = result * PRIME + ($mediaType == null ? 43 : $mediaType.hashCode());
        final long $usagePerMinute = Double.doubleToLongBits(this.getUsagePerMinute());
        result = result * PRIME + (int) ($usagePerMinute >>> 32 ^ $usagePerMinute);
        final Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof MediaUsageDto;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.MediaUsageDto(mediaType=" + this.getMediaType()
                + ", usagePerMinute=" + this.getUsagePerMinute() + ", location=" + this.getLocation() + ")";
    }
}
