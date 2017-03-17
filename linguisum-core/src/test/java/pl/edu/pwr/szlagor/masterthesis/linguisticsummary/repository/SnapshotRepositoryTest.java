package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.repository;

import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.config.TestMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.business.snapshot.SnapshotService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Device;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.EnvironmentConditions;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.MediaUsage;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.SnapshotDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.DeviceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;

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
    private SnapshotService snapshotService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RoomRepository roomRepository;

    @After
    public void tearDown(){
        mongoTemplate.dropCollection("snapshot");
    }

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
                Device device = new Device(DEVICE_ID, "deviceName", MediaType.ELECTRICITY, 0.1d);

                entity.setWeatherConditions(env);
                DeviceState deviceState = new DeviceState(device, true);
                entity.setPersonStates(newHashSet(new PersonState(PERSON_ID, 1L), new PersonState(2L, 2L)));
                entity.setRoomStates(newHashSet(new RoomState(room, person, 21.5d), new RoomState(room, person, 18.5d), new RoomState(room, person,
                        19.5d), new RoomState(room, person, 21.0d), new RoomState(room, person, 22.0d), new RoomState(room, person, 18.5d)));
                entity.setDeviceStates(newHashSet(deviceState));
                entity.setMediaUsages(newHashSet(new MediaUsage(MediaType.ELECTRICITY, 5.5d, room), new MediaUsage
                                                         (MediaType.ELECTRICITY, 12.5d, room), new MediaUsage(MediaType.ELECTRICITY, 2.4d, room), new
                                                         MediaUsage(MediaType.HOT_WATER, 6.5d, room), new MediaUsage(MediaType.COLD_WATER, 9.5d, room),
                                                 new MediaUsage(MediaType.GAS, 4.25d, room)));
                entity.setDate(LocalDateTime.now().toLocalDate());
                entity.setTime(LocalDateTime.now().toLocalTime());
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

    @Test
    public void shouldFindByDate(){
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = now.toLocalDate();
        Snapshot snapshot = Snapshot.builder().time(now.toLocalTime()).personState(PersonState.builder().locationId(ROOM_ID).userId(PERSON_ID).build()).build();
        // when
        snapshotRepository.save(snapshot);
        // then
        List<Snapshot> found = snapshotRepository.findByDate(date);
        assertThat(found, Matchers.hasSize(1));
        assertThat(found, hasItem(hasProperty("timestamp", equalTo(now))));
    }

    @Test
    public void shouldFindByUserState(){
        // given
        LocalDateTime now = LocalDateTime.now();
        PersonState personState = PersonState.builder().locationId(ROOM_ID).userId(PERSON_ID).build();
        Snapshot snapshot = Snapshot.builder().time(now.toLocalTime()).personState(personState).personState(PersonState.builder().userId(98L).locationId(99L).build()).build();
        // when
        snapshotRepository.save(snapshot);
        // then
        List<Snapshot> found = snapshotRepository.findByPersonStatesContaining(personState);
        assertThat(found, Matchers.hasSize(1));
        assertThat(found, hasItem(hasProperty("timestamp", equalTo(now))));
        assertThat(found, hasItem(hasProperty("personStates", hasItem(equalTo(personState)))));
    }

    @Test
    public void shouldFindByUserStates(){
        // given
        LocalDateTime now = LocalDateTime.now();
        PersonState personState = PersonState.builder().locationId(ROOM_ID).userId(PERSON_ID).build();
        PersonState personState1 = PersonState.builder().userId(98L).locationId(99L).build();
        Snapshot snapshot = Snapshot.builder().time(now.toLocalTime()).personState(personState).personState(personState1).build();
        // when
        snapshotRepository.save(snapshot);
        // then
        List<Snapshot> found = snapshotRepository.findByPersonStatesContaining(personState, personState1);
        assertThat(found, Matchers.hasSize(1));
        assertThat(found, hasItem(hasProperty("timestamp", equalTo(now))));
        assertThat(found, hasItem(hasProperty("personStates", hasSize(2))));
        assertThat(found, hasItem(hasProperty("personStates", hasItem(equalTo(personState)))));
        assertThat(found, hasItem(hasProperty("personStates", hasItem(equalTo(personState1)))));
    }

    @Test
    public void shouldFindAllEntities() {
        final Page<Snapshot> page = snapshotRepository.findAll(new PageRequest(0, 10000));

    }

    @Test
    public void shouldFindAllWithService() {
        final List<SnapshotDto> allInBulk = snapshotService.findAll();
    }
}