package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Immutable;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Pawel on 2017-01-15.
 */
@Immutable
@Document(collection = "snapshot")
@Entity
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @Indexed
    private LocalDate date;

    @Indexed
    private LocalTime time;

    @IndexedEmbedded
    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<PersonState> personStates;

    @Embedded
    private EnvironmentConditions weatherConditions;

    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<DeviceState> deviceStates;

    @IndexedEmbedded
    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<RoomState> roomStates;

    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<MediaUsage> mediaUsages;

    @java.beans.ConstructorProperties({ "id", "date", "time", "personStates", "weatherConditions", "deviceStates", "roomStates",
                                        "mediaUsages" })
    public Snapshot(BigInteger id,
            LocalDate date,
            LocalTime time,
            Set<PersonState> personStates,
            EnvironmentConditions weatherConditions,
            Set<DeviceState> deviceStates,
            Set<RoomState> roomStates,
            Set<MediaUsage> mediaUsages) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.personStates = personStates;
        this.weatherConditions = weatherConditions;
        this.deviceStates = deviceStates;
        this.roomStates = roomStates;
        this.mediaUsages = mediaUsages;
    }

    public Snapshot() {
    }

    public static SnapshotBuilder builder() {
        return new SnapshotBuilder();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Set<PersonState> getPersonStates() {
        return personStates;
    }

    public void setPersonStates(Set<PersonState> personStates) {
        this.personStates = personStates;
    }

    public EnvironmentConditions getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(EnvironmentConditions weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public Set<DeviceState> getDeviceStates() {
        return deviceStates;
    }

    public void setDeviceStates(Set<DeviceState> deviceStates) {
        this.deviceStates = deviceStates;
    }

    public Set<RoomState> getRoomStates() {
        return roomStates;
    }

    public void setRoomStates(Set<RoomState> roomStates) {
        this.roomStates = roomStates;
    }

    public Set<MediaUsage> getMediaUsages() {
        return mediaUsages;
    }

    public void setMediaUsages(Set<MediaUsage> mediaUsages) {
        this.mediaUsages = mediaUsages;
    }

    public static class SnapshotBuilder {
        private BigInteger id;
        private LocalDate date;
        private LocalTime time;
        private ArrayList<PersonState> personStates;
        private EnvironmentConditions weatherConditions;
        private Set<DeviceState> deviceStates;
        private Set<RoomState> roomStates;
        private Set<MediaUsage> mediaUsages;

        SnapshotBuilder() {
        }

        public Snapshot.SnapshotBuilder id(BigInteger id) {
            this.id = id;
            return this;
        }

        public Snapshot.SnapshotBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Snapshot.SnapshotBuilder time(LocalTime time) {
            this.time = time;
            return this;
        }

        public Snapshot.SnapshotBuilder personState(PersonState personState) {
            if (this.personStates == null)
                this.personStates = new ArrayList<PersonState>();
            this.personStates.add(personState);
            return this;
        }

        public Snapshot.SnapshotBuilder personStates(Collection<? extends PersonState> personStates) {
            if (this.personStates == null)
                this.personStates = new ArrayList<PersonState>();
            this.personStates.addAll(personStates);
            return this;
        }

        public Snapshot.SnapshotBuilder clearPersonStates() {
            if (this.personStates != null)
                this.personStates.clear();

            return this;
        }

        public Snapshot.SnapshotBuilder weatherConditions(EnvironmentConditions weatherConditions) {
            this.weatherConditions = weatherConditions;
            return this;
        }

        public Snapshot.SnapshotBuilder deviceStates(Set<DeviceState> deviceStates) {
            this.deviceStates = deviceStates;
            return this;
        }

        public Snapshot.SnapshotBuilder roomStates(Set<RoomState> roomStates) {
            this.roomStates = roomStates;
            return this;
        }

        public Snapshot.SnapshotBuilder mediaUsages(Set<MediaUsage> mediaUsages) {
            this.mediaUsages = mediaUsages;
            return this;
        }

        public Snapshot build() {
            Set<PersonState> personStates;
            switch (this.personStates == null ? 0 : this.personStates.size()) {
                case 0:
                    personStates = java.util.Collections.emptySet();
                    break;
                case 1:
                    personStates = java.util.Collections.singleton(this.personStates.get(0));
                    break;
                default:
                    personStates = new java.util.LinkedHashSet<PersonState>(this.personStates.size() < 1073741824
                            ? 1 + this.personStates.size() + (this.personStates.size() - 3) / 3 : Integer.MAX_VALUE);
                    personStates.addAll(this.personStates);
                    personStates = java.util.Collections.unmodifiableSet(personStates);
            }

            return new Snapshot(id, date, time, personStates, weatherConditions, deviceStates, roomStates, mediaUsages);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot.SnapshotBuilder(id=" + this.id + ", date="
                    + this.date + ", time=" + this.time + ", personStates=" + this.personStates + ", weatherConditions="
                    + this.weatherConditions + ", deviceStates=" + this.deviceStates + ", roomStates=" + this.roomStates + ", mediaUsages="
                    + this.mediaUsages + ")";
        }
    }
}
