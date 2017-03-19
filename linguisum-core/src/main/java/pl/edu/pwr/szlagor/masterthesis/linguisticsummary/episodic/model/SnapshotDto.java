package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.bson.types.ObjectId;

/**
 * Created by Pawel on 2017-01-15.
 */
public class SnapshotDto {

    private ObjectId id;

    private LocalDate date;

    private LocalTime time;

    private Set<PersonStateDto> personStates;

    private EnvironmentConditionsDto weatherConditions;

    private Set<DeviceStateDto> deviceStates;

    private Set<RoomStateDto> roomStates;

    private Set<MediaUsageDto> mediaUsages;

    @java.beans.ConstructorProperties({ "id", "date", "time", "personStates", "weatherConditions", "deviceStates", "roomStates",
                                        "mediaUsages" })
    public SnapshotDto(ObjectId id,
            LocalDate date,
            LocalTime time,
            Set<PersonStateDto> personStates,
            EnvironmentConditionsDto weatherConditions,
            Set<DeviceStateDto> deviceStates,
            Set<RoomStateDto> roomStates,
            Set<MediaUsageDto> mediaUsages) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.personStates = personStates;
        this.weatherConditions = weatherConditions;
        this.deviceStates = deviceStates;
        this.roomStates = roomStates;
        this.mediaUsages = mediaUsages;
    }

    public SnapshotDto() {
    }

    public static SnapshotDtoBuilder builder() {
        return new SnapshotDtoBuilder();
    }

    public ObjectId getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public Set<PersonStateDto> getPersonStates() {
        return this.personStates;
    }

    public EnvironmentConditionsDto getWeatherConditions() {
        return this.weatherConditions;
    }

    public Set<DeviceStateDto> getDeviceStates() {
        return this.deviceStates;
    }

    public Set<RoomStateDto> getRoomStates() {
        return this.roomStates;
    }

    public Set<MediaUsageDto> getMediaUsages() {
        return this.mediaUsages;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setPersonStates(Set<PersonStateDto> personStates) {
        this.personStates = personStates;
    }

    public void setWeatherConditions(EnvironmentConditionsDto weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public void setDeviceStates(Set<DeviceStateDto> deviceStates) {
        this.deviceStates = deviceStates;
    }

    public void setRoomStates(Set<RoomStateDto> roomStates) {
        this.roomStates = roomStates;
    }

    public void setMediaUsages(Set<MediaUsageDto> mediaUsages) {
        this.mediaUsages = mediaUsages;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SnapshotDto))
            return false;
        final SnapshotDto other = (SnapshotDto) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id))
            return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date))
            return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null ? other$time != null : !this$time.equals(other$time))
            return false;
        final Object this$personStates = this.getPersonStates();
        final Object other$personStates = other.getPersonStates();
        if (this$personStates == null ? other$personStates != null : !this$personStates.equals(other$personStates))
            return false;
        final Object this$weatherConditions = this.getWeatherConditions();
        final Object other$weatherConditions = other.getWeatherConditions();
        if (this$weatherConditions == null ? other$weatherConditions != null : !this$weatherConditions.equals(other$weatherConditions))
            return false;
        final Object this$deviceStates = this.getDeviceStates();
        final Object other$deviceStates = other.getDeviceStates();
        if (this$deviceStates == null ? other$deviceStates != null : !this$deviceStates.equals(other$deviceStates))
            return false;
        final Object this$roomStates = this.getRoomStates();
        final Object other$roomStates = other.getRoomStates();
        if (this$roomStates == null ? other$roomStates != null : !this$roomStates.equals(other$roomStates))
            return false;
        final Object this$mediaUsages = this.getMediaUsages();
        final Object other$mediaUsages = other.getMediaUsages();
        if (this$mediaUsages == null ? other$mediaUsages != null : !this$mediaUsages.equals(other$mediaUsages))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        final Object $personStates = this.getPersonStates();
        result = result * PRIME + ($personStates == null ? 43 : $personStates.hashCode());
        final Object $weatherConditions = this.getWeatherConditions();
        result = result * PRIME + ($weatherConditions == null ? 43 : $weatherConditions.hashCode());
        final Object $deviceStates = this.getDeviceStates();
        result = result * PRIME + ($deviceStates == null ? 43 : $deviceStates.hashCode());
        final Object $roomStates = this.getRoomStates();
        result = result * PRIME + ($roomStates == null ? 43 : $roomStates.hashCode());
        final Object $mediaUsages = this.getMediaUsages();
        result = result * PRIME + ($mediaUsages == null ? 43 : $mediaUsages.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof SnapshotDto;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.SnapshotDto(id=" + this.getId() + ", date="
                + this.getDate() + ", time=" + this.getTime() + ", personStates=" + this.getPersonStates() + ", weatherConditions="
                + this.getWeatherConditions() + ", deviceStates=" + this.getDeviceStates() + ", roomStates=" + this.getRoomStates()
                + ", mediaUsages=" + this.getMediaUsages() + ")";
    }

    public static class SnapshotDtoBuilder {
        private ObjectId id;
        private LocalDate date;
        private LocalTime time;
        private Set<PersonStateDto> personStates;
        private EnvironmentConditionsDto weatherConditions;
        private Set<DeviceStateDto> deviceStates;
        private Set<RoomStateDto> roomStates;
        private Set<MediaUsageDto> mediaUsages;

        SnapshotDtoBuilder() {
        }

        public SnapshotDto.SnapshotDtoBuilder id(ObjectId id) {
            this.id = id;
            return this;
        }

        public SnapshotDto.SnapshotDtoBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public SnapshotDto.SnapshotDtoBuilder time(LocalTime time) {
            this.time = time;
            return this;
        }

        public SnapshotDto.SnapshotDtoBuilder personStates(Set<PersonStateDto> personStates) {
            this.personStates = personStates;
            return this;
        }

        public SnapshotDto.SnapshotDtoBuilder weatherConditions(EnvironmentConditionsDto weatherConditions) {
            this.weatherConditions = weatherConditions;
            return this;
        }

        public SnapshotDto.SnapshotDtoBuilder deviceStates(Set<DeviceStateDto> deviceStates) {
            this.deviceStates = deviceStates;
            return this;
        }

        public SnapshotDto.SnapshotDtoBuilder roomStates(Set<RoomStateDto> roomStates) {
            this.roomStates = roomStates;
            return this;
        }

        public SnapshotDto.SnapshotDtoBuilder mediaUsages(Set<MediaUsageDto> mediaUsages) {
            this.mediaUsages = mediaUsages;
            return this;
        }

        public SnapshotDto build() {
            return new SnapshotDto(id, date, time, personStates, weatherConditions, deviceStates, roomStates, mediaUsages);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.SnapshotDto.SnapshotDtoBuilder(id=" + this.id
                    + ", date=" + this.date + ", time=" + this.time + ", personStates=" + this.personStates + ", weatherConditions="
                    + this.weatherConditions + ", deviceStates=" + this.deviceStates + ", roomStates=" + this.roomStates + ", mediaUsages="
                    + this.mediaUsages + ")";
        }
    }
}
