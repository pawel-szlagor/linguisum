package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.processor;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DesiredTempSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceStateSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.MediaUsageSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonPositionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.RoomSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.SnapshotSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.WeatherConditionSourceDto;

/**
 * Created by Pawel on 2017-02-08.
 */
@StepScope
@Component
public class IntegratorProcessor implements ItemProcessor<SnapshotSourceDto, Snapshot> {

    private MapperFacade mapperFacade = initializeMapperFacade();
    private final List<Room> allRoomsList;

    @Autowired
    public IntegratorProcessor(RoomRepository roomRepository) {
        allRoomsList = roomRepository.findAll();
    }

    @Override
    public synchronized Snapshot process(SnapshotSourceDto item) {
        return Snapshot.builder().deviceStates(item.getDeviceStates().stream().map(mapDeviceStateFunction()).collect(Collectors.toSet()))
                .mediaUsages(item.getMediaUsages().stream().map(mediaUsageSourceDtoMediaUsageFunction()).collect(Collectors.toSet()))
                .personStates(item.getPersonPositions().stream().map(personPositionSourceDtoPersonStateFunction()).collect(Collectors.toSet()))
                       .roomStates(processRoomStates(item))
                .weatherConditions(mapperFacade.map(item.getWeatherConditions(), EnvironmentConditions.class))
                .date(item.getObservationTime().toLocalDate())
                .time(item.getObservationTime().toLocalTime())
                .build();
    }

    private Set<RoomState> processRoomStates(SnapshotSourceDto item) {
        final List<Long> roomsIds = item.getDesiredTemps().stream().map(d -> d.getLocation().getId()).collect(Collectors.toList());
        final Set<RoomState> roomsWithTempSet = item.getDesiredTemps()
                                                    .stream()
                                                    .map(desiredTempSourceDtoRoomStateFunction())
                                                    .collect(Collectors.toSet());
        final Set<RoomState> roomWithTempNotSet = allRoomsList.stream()
                                                              .filter(r -> !roomsIds.contains(r.getId()))
                                                              .map(r -> RoomState.builder().room(r).build())
                                                              .collect(Collectors.toSet());
        roomsWithTempSet.addAll(roomWithTempNotSet);
        return roomsWithTempSet;
    }

    private Function<MediaUsageSourceDto, MediaUsage> mediaUsageSourceDtoMediaUsageFunction() {
        return l -> MediaUsage.builder().location(mapperFacade.map(l.getLocation(), Room.class)).mediaType(l.getMediaType()).usagePerMinute(l.getUsagePerMinute()).build();
    }

    private Function<PersonPositionSourceDto, PersonState> personPositionSourceDtoPersonStateFunction() {
        return l -> PersonState.builder()
                               .location(mapperFacade.map(l.getLocation(), Room.class))
                               .user(mapperFacade.map(l.getUser(), Person.class))
                               .build();
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
