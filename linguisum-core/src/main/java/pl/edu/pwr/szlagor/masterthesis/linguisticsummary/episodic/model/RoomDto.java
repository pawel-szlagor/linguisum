package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;

/**
 * Created by Pawel on 2017-01-15.
 */
public class RoomDto {

    private Long id;
    private String name;
    private RoomType roomType;

    @java.beans.ConstructorProperties({ "id", "name", "roomType" })
    public RoomDto(Long id, String name, RoomType roomType) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
    }

    public RoomDto() {
    }

    public static RoomDtoBuilder builder() {
        return new RoomDtoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RoomDto))
            return false;
        final RoomDto other = (RoomDto) o;
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
        final Object this$roomType = this.getRoomType();
        final Object other$roomType = other.getRoomType();
        if (this$roomType == null ? other$roomType != null : !this$roomType.equals(other$roomType))
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
        final Object $roomType = this.getRoomType();
        result = result * PRIME + ($roomType == null ? 43 : $roomType.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoomDto;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomDto(id=" + this.getId() + ", name=" + this.getName()
                + ", roomType=" + this.getRoomType() + ")";
    }

    public static class RoomDtoBuilder {
        private Long id;
        private String name;
        private RoomType roomType;

        RoomDtoBuilder() {
        }

        public RoomDto.RoomDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoomDto.RoomDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RoomDto.RoomDtoBuilder roomType(RoomType roomType) {
            this.roomType = roomType;
            return this;
        }

        public RoomDto build() {
            return new RoomDto(id, name, roomType);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomDto.RoomDtoBuilder(id=" + this.id + ", name="
                    + this.name + ", roomType=" + this.roomType + ")";
        }
    }
}
