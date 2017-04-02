package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;

/**
 * Created by Pawel on 2017-01-15.
 */
@Document
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private RoomType type;

    @java.beans.ConstructorProperties({ "id", "name", "type" })
    public Room(Long id, String name, RoomType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Room() {
    }

    public static RoomBuilder builder() {
        return new RoomBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Room room = (Room) o;

        return id != null ? id.equals(room.id) : room.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Room;
    }

    public String toString() {
        return "Room(id=" + this.getId() + ", name=" + this.getName()
                + ", type=" + this.getType() + ")";
    }

    public static class RoomBuilder {
        private Long id;
        private String name;
        private RoomType type;

        RoomBuilder() {
        }

        public Room.RoomBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Room.RoomBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Room.RoomBuilder type(RoomType type) {
            this.type = type;
            return this;
        }

        public Room build() {
            return new Room(id, name, type);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room.RoomBuilder(id=" + this.id
					+ ", name=" + this.name
                    + ", type=" + this.type + ")";
        }
    }
}
