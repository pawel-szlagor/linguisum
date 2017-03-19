package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;

/**
 * Created by Pawel on 2017-01-16.
 */
public class DeviceDto {

    private Long id;
    private String name;
    private MediaType mediaType;
    private double mediaUsage;

    @java.beans.ConstructorProperties({ "id", "name", "mediaType", "mediaUsage" })
    public DeviceDto(Long id, String name, MediaType mediaType, double mediaUsage) {
        this.id = id;
        this.name = name;
        this.mediaType = mediaType;
        this.mediaUsage = mediaUsage;
    }

    public DeviceDto() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public double getMediaUsage() {
        return this.mediaUsage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setMediaUsage(double mediaUsage) {
        this.mediaUsage = mediaUsage;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DeviceDto))
            return false;
        final DeviceDto other = (DeviceDto) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name))
            return false;
        final Object this$mediaType = this.getMediaType();
        final Object other$mediaType = other.getMediaType();
        if (this$mediaType == null ? other$mediaType != null : !this$mediaType.equals(other$mediaType))
            return false;
        if (Double.compare(this.getMediaUsage(), other.getMediaUsage()) != 0)
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $mediaType = this.getMediaType();
        result = result * PRIME + ($mediaType == null ? 43 : $mediaType.hashCode());
        final long $mediaUsage = Double.doubleToLongBits(this.getMediaUsage());
        result = result * PRIME + (int) ($mediaUsage >>> 32 ^ $mediaUsage);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof DeviceDto;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceDto(id=" + this.getId() + ", name=" + this.getName()
                + ", mediaType=" + this.getMediaType() + ", mediaUsage=" + this.getMediaUsage() + ")";
    }
}
