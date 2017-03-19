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

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Room))
            return false;
        final Room other = (Room) o;
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
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type))
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
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Room;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room(id=" + this.getId() + ", name=" + this.getName()
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
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room.RoomBuilder(id=" + this.id + ", name=" + this.name
                    + ", type=" + this.type + ")";
        }
    }
}
