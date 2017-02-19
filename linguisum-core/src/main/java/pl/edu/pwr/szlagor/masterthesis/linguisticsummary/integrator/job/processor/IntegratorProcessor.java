package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.processor;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
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
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceStateSourceDto;
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

    @Override
    public Snapshot process(SnapshotSourceDto item) {
        try {
            return Snapshot.builder().deviceStates(item.getDeviceStates().stream().map(mapDeviceStateFunction()).collect(Collectors.toSet()))
                    .mediaUsages(item.getMediaUsages().stream().map(l -> MediaUsage.builder().locationId(l.getLocation().getId()).mediaType(l.getMediaType()).usagePerMinute(l.getUsagePerMinute()).build()).collect(Collectors.toSet()))
                    .personStates(item.getPersonPositions().stream().map(l -> PersonState.builder().locationId(l.getLocation().getId()).userId(l.getUser().getId()).build()).collect(Collectors.toSet()))
                    .roomStates(item.getDesiredTemps().stream().map(l -> RoomState.builder().desiredTemp(l.getDesiredTemp()).personId(mapperFacade.map(l.getUser(), Person.class)).roomId(mapperFacade.map(l.getLocation(), Room.class)).build()).collect(Collectors.toSet()))
                    .weatherConditions(mapperFacade.map(item.getWeatherConditions(), EnvironmentConditions.class))
                    .timestamp(item.getObservationTime())
                    .build();
        }catch (Exception ex){
            return null;
        }
    }

    private Function<DeviceStateSourceDto, DeviceState> mapDeviceStateFunction() {
        return l -> DeviceState.builder().deviceId(mapperFacade.map(l.getDevice(), Device.class)).roomId(mapperFacade.map(l.getLocation(), Room.class)).isOn(l.isWorking()).build();
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
