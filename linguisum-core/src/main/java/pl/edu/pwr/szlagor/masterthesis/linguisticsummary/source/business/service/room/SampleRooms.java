package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.RoomSourceDto;

/**
 * Created by Pawel on 2017-01-31.
 */
public class SampleRooms {
    public static final RoomSourceDto KITCHEN = RoomSourceDto.builder().name("kitchen").type(RoomType.KITCHEN).id(1L).build();
    public static final RoomSourceDto BATHROOM = RoomSourceDto.builder().name("bathroom").type(RoomType.BATHROOM).id(2L).build();
    public static final RoomSourceDto DINING_ROOM = RoomSourceDto.builder().name("dining room").type(RoomType.DINING_ROOM).id(3L).build();
    public static final RoomSourceDto LIVING_ROOM = RoomSourceDto.builder().name("livingRoo1").type(RoomType.LIVING_ROOM).id(4L).build();
    public static final RoomSourceDto SLEEPING_ROOM_1 = RoomSourceDto.builder().name("sleepingRoom1").type(RoomType.SLEEPING_ROOM).id(5L).build();
    public static final RoomSourceDto SLEEPING_ROOM_2 = RoomSourceDto.builder().name("sleepingRoom2").type(RoomType.SLEEPING_ROOM).id(6L).build();
    public static final RoomSourceDto SLEEPING_ROOM_3 = RoomSourceDto.builder().name("sleepingRoom3").type(RoomType.SLEEPING_ROOM).id(7L).build();
    public static final RoomSourceDto HALL = RoomSourceDto.builder().name("hall").type(RoomType.HALL).id(8L).build();
    public static final RoomSourceDto TOILETTE = RoomSourceDto.builder().name("toilette").type(RoomType.TOILETTE).id(9L).build();

}
