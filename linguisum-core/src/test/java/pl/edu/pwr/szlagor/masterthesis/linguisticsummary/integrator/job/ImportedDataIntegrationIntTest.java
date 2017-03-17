package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Device;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.EnvironmentConditions;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.MediaUsage;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.config.BatchConfiguration;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DesiredTempSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceStateSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.MediaUsageSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonPositionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.RoomSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.SnapshotSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.WeatherConditionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.desiredtemp.DesiredTempSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.devicestate.DeviceStateSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.mediausage.MediaUsageSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.personposition.PersonPositionSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.WeatherConditionsService;

/**
 * Created by Pawel on 2017-02-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BatchConfiguration.class})
public class ImportedDataIntegrationIntTest {
    private static final LocalDateTime INITIAL_TIME = LocalDate.of(2016, 1, 1).atStartOfDay();
    private LocalDateTime currentTime;
    private final int SECONDS_DURATION = 366 * 24 * 60 * 60;
    private static final Random random = new Random();
    private MapperFacade mapperFacade = initializeMapperFacade();

    @Autowired
    private SnapshotRepository snapshotRepository;
    @Autowired
    private DesiredTempSourceService desiredTempSourceService;
    @Autowired
    private DeviceStateSourceService deviceStateSourceService;
    @Autowired
    private MediaUsageSourceService mediaUsageSourceService;
    @Autowired
    private PersonPositionSourceService personPositionSourceService;
    @Autowired
    private WeatherConditionsService weatherConditionsService;

    @Test
    @Repeat(value = 1000)
    public void shouldImportDataConsolidately() {
        // given
        LocalDateTime testTime = INITIAL_TIME.plusSeconds(random.nextInt(SECONDS_DURATION / 5) * 5);
        System.out.println("Testtime: " + testTime);
        // when
        final Snapshot snapshot = snapshotRepository.findByDateAndTime(testTime.toLocalDate(), testTime.toLocalTime());
        // then
        assertThat(snapshot.getWeatherConditions(), equalTo(mapperFacade.map(weatherConditionsService.findByDateTime(testTime), EnvironmentConditions.class)));
        final Set<DeviceStateSourceDto> deviceStateSourceDtos = deviceStateSourceService.findByObservationTimeAround(testTime);
        assertThat(snapshot.getDeviceStates(), hasSize(deviceStateSourceDtos.size()));
        deviceStateSourceDtos.stream().map(mapDeviceStateFunction()).forEach(l -> assertThat(snapshot.getDeviceStates(), hasItem(equalTo(l))));
        final Set<DesiredTempSourceDto> desiredTempSourceDtos = desiredTempSourceService.findByObservationTimeAround(testTime);
        assertThat(snapshot.getRoomStates(), hasSize(desiredTempSourceDtos.size()));
        desiredTempSourceDtos.stream().map(desiredTempSourceDtoRoomStateFunction()).forEach(l -> assertThat(snapshot.getRoomStates(), hasItem(equalTo(l))));
        final Set<MediaUsageSourceDto> mediaUsageSourceDtos = mediaUsageSourceService.findByObservationTimeAround(testTime);
        assertThat(snapshot.getMediaUsages(), hasSize(mediaUsageSourceDtos.size()));
        mediaUsageSourceDtos.stream().map(mediaUsageSourceDtoMediaUsageFunction()).forEach(l -> assertThat(snapshot.getMediaUsages(), hasItem(equalTo(l))));
        final Set<PersonPositionSourceDto> personPositionSourceDtos = personPositionSourceService.findByObservationTimeAround(testTime);
        assertThat(snapshot.getPersonStates(), hasSize(personPositionSourceDtos.size()));
        personPositionSourceDtos.stream().map(personPositionSourceDtoPersonStateFunction()).forEach(l -> assertThat(snapshot.getPersonStates(), hasItem(equalTo(l))));
    }

    private Function<MediaUsageSourceDto, MediaUsage> mediaUsageSourceDtoMediaUsageFunction() {
        return l -> MediaUsage.builder().location(mapperFacade.map(l.getLocation(), Room.class)).mediaType(l.getMediaType()).usagePerMinute(l.getUsagePerMinute()).build();
    }

    private Function<PersonPositionSourceDto, PersonState> personPositionSourceDtoPersonStateFunction() {
        return l -> PersonState.builder().locationId(l.getLocation().getId()).userId(l.getUser().getId()).build();
    }

    private Function<DesiredTempSourceDto, RoomState> desiredTempSourceDtoRoomStateFunction() {
        return l -> RoomState.builder().desiredTemp(l.getDesiredTemp()).person(mapperFacade.map(l.getUser(), Person.class)).room(mapperFacade.map(l.getLocation(), Room.class)).build();
    }

    private Function<DeviceStateSourceDto, DeviceState> mapDeviceStateFunction() {
        return l -> DeviceState.builder().device(mapperFacade.map(l.getDevice(), Device.class)).isOn(l.isWorking()).build();
    }

    private MapperFacade initializeMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(SnapshotSourceDto.class, Snapshot.class)
                .byDefault().register();
        factory.classMap(WeatherConditionSourceDto.class, EnvironmentConditions.class)
                .byDefault().register();
        factory.classMap(RoomSourceDto.class, Room.class)
                .byDefault().register();
        factory.classMap(DeviceStateSourceDto.class, DeviceState.class)
                .byDefault().register();
        return factory.getMapperFacade();
    }
}
