package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.config.TestMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.*;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.DeviceType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.DeviceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Created by Pawel on 2017-01-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestMongoConfig.class)
public class SnapshotRepositoryTest {

    private static final long PERSON_ID = 1L;
    private static final long ROOM_ID = 1L;
    private static final double FALL = 3.0d;
    private static final int HUMIDITY = 80;
    private static final int PRESSURE = 1012;
    private static final double TEMP_OUTSIDE = -5d;
    private static final double WIND_SPEED = 5.6d;
    private static final double SUNLIGHT = 42d;
    private static final long DEVICE_ID = 5L;
    private static final double WIND_CHILL = -7.5d;

    @Autowired
    private SnapshotRepository snapshotRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void shouldFindSpanshotByUser() {
        // given
        Collection<Snapshot> col = new HashSet<>();
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < 9; i++) {
                Snapshot entity = new Snapshot();
                EnvironmentConditions env = EnvironmentConditions.builder().precipitation(FALL).weatherEvent(WeatherEvent.RAIN)
                        .humidity(HUMIDITY).pressure(PRESSURE).tempOut(TEMP_OUTSIDE).weatherEvent(WeatherEvent
                                .FOG).windSpeed(WIND_SPEED).windchill(WIND_CHILL).sunlightEmission
                                (SUNLIGHT).build();

                Person person = new Person(PERSON_ID, "name", "mail");
                Room room = new Room(ROOM_ID, "name", RoomType.LIVING_ROOM);
                Device device = new Device(DEVICE_ID, "deviceName", DeviceType.ELECTRIC_KETTEL);

                entity.setWeatherConditions(env);
                DeviceState deviceState = new DeviceState(device, room, true);
                entity.setPersonStates(newHashSet(new PersonState(PERSON_ID, 1L), new PersonState(2L, 2L)));
                entity.setRoomStates(newHashSet(new RoomState(room, person, 21.5d), new RoomState(room, person, 18.5d), new RoomState(room, person,
                        19.5d), new RoomState(room, person, 21.0d), new RoomState(room, person, 22.0d), new RoomState(room, person, 18.5d)));
                entity.setDeviceStates(newHashSet(deviceState));
                entity.setMediaUsages(newHashSet(new MediaUsage(MediaType.ELECTRICITY, 5.5d, 1L), new MediaUsage
                                (MediaType.ELECTRICITY, 12.5d, 4L), new MediaUsage(MediaType.ELECTRICITY, 2.4d, 6L), new
                                MediaUsage(MediaType.HOT_WATER, 6.5d, 5L), new MediaUsage(MediaType.COLD_WATER, 9.5d, 5L),
                        new MediaUsage(MediaType.GAS, 4.25d, 6L)));
                entity.setTimestamp(LocalDateTime.now());
                col.add(entity);
                // when
/*        deviceRepository.save(new Device(1L, "czajnik elektryczny", ELECTRIC_KETTEL));
        deviceRepository.save(new Device(2L, "pralka", WASH_MACHINE));
        deviceRepository.save(new Device(3L, "kuchenka elektrycnza", OVEN));
        deviceRepository.save(new Device(4L, "komputer stacjonarny", PC));
        deviceRepository.save(new Device(5L, "laptop", LAPTOP));

        roomRepository.save(new Room(1L, "pokój 1", SLEEPING_ROOM));
        roomRepository.save(new Room(2L, "pokój 2", SLEEPING_ROOM));
        roomRepository.save(new Room(3L, "hall", HALL));
        roomRepository.save(new Room(4L, "pokój dzienny", LIVING_ROOM));
        roomRepository.save(new Room(5L, "łazienka", BATHROOM));
        roomRepository.save(new Room(6L, "kuchnia", KITCHEN));*/

            }
            snapshotRepository.save(col);
        }
        // then
        //snapshotRepository.findByPersonStatesPersonId(PERSON_ID);
        snapshotRepository.count();
    }

}