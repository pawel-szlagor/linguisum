package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy;

import static java.util.stream.Collectors.toSet;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.COLD_WATER;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.DES_TEMP;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.ELECTRICITY;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.HOT_WATER;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.HUMIDITY;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.PRECIPITATION;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.PRESSURE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.SUNLIGHT;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.TEMP_OUT;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeTypes.WIND_SPEED;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.EnvironmentConditions;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.MediaUsage;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Pawel on 2017-03-23.
 */
@Component
public class FSnapshotConverter implements Converter<Snapshot, FSnapshot> {

    private final MemGradeService memGradeService;

    @Autowired
    public FSnapshotConverter(MemGradeService memGradeService) {
        this.memGradeService = memGradeService;
    }

    @Override
    public FSnapshot convert(Snapshot source) {
        return FSnapshot.builder()
                        .time(source.getTime())
                        .date(source.getDate())
                        .deviceStates(source.getDeviceStates())
                        .id(source.getId())
                        .mediaUsages(source.getMediaUsages().stream().map(this::convertMediaUsage).collect(Collectors.toSet()))
                        .deviceStates(source.getDeviceStates())
                        .personStates(source.getPersonStates())
                        .roomStates(source.getRoomStates().stream().map(this::convertRoomStates).collect(toSet()))
                        .weatherConditions(convertWeatherConditions(source.getWeatherConditions()))
                        .build();
    }

    private FMediaUsage convertMediaUsage(MediaUsage mediaUsage) {
        switch (mediaUsage.getMediaType()) {
            case COLD_WATER:
                return FMediaUsage.builder()
                                  .mediaType(mediaUsage.getMediaType())
                                  .location(mediaUsage.getLocation())
                                  .fUsagePerMinute(
                                          memGradeService.findByProperty(COLD_WATER.name())
                                                         .stream()
                                                         .filter(m -> m.isBelonging(mediaUsage.getUsagePerMinute()))
                                                         .collect(toSet()))
                                  .build();
            case ELECTRICITY:
                return FMediaUsage.builder()
                                  .mediaType(mediaUsage.getMediaType())
                                  .location(mediaUsage.getLocation())
                                  .fUsagePerMinute(
                                          memGradeService.findByProperty(ELECTRICITY.name())
                                                         .stream()
                                                         .filter(m -> m.isBelonging(mediaUsage.getUsagePerMinute()))
                                                         .collect(toSet()))
                                  .build();
            case HOT_WATER:
                return FMediaUsage.builder()
                                  .mediaType(mediaUsage.getMediaType())
                                  .location(mediaUsage.getLocation())
                                  .fUsagePerMinute(
                                          memGradeService.findByProperty(HOT_WATER.name())
                                                         .stream()
                                                         .filter(m -> m.isBelonging(mediaUsage.getUsagePerMinute()))
                                                         .collect(toSet()))
                                  .build();
            default:
                throw new IllegalArgumentException("Media Type not known");
        }
    }

    private FRoomState convertRoomStates(RoomState roomState) {
        return FRoomState.builder()
                         .person(roomState.getPerson())
                         .room(roomState.getRoom())
                         .fDesiredTemp(memGradeService.findByProperty(DES_TEMP.name())
                                                      .stream()
                                                      .filter(m -> m.isBelonging(roomState.getDesiredTemp()))
                                                      .collect(toSet()))
                         .build();
    }

    private FEnvironmentConditions convertWeatherConditions(EnvironmentConditions source) {
        return FEnvironmentConditions.builder()
                                     .fHumidity(memGradeService.findByProperty(HUMIDITY.name())
                                                               .stream()
                                                               .filter(m -> m.isBelonging(source.getHumidity()))
                                                               .collect(toSet()))
                                     .fPrecipitation(memGradeService.findByProperty(PRECIPITATION.name())
                                                                    .stream()
                                                                    .filter(m -> m.isBelonging(source.getPrecipitation()))
                                                                    .collect(toSet()))
                                     .fPressure(memGradeService.findByProperty(PRESSURE.name())
                                                               .stream()
                                                               .filter(m -> m.isBelonging(source.getPressure()))
                                                               .collect(toSet()))
                                     .fSunlightEmission(
                                             memGradeService.findByProperty(SUNLIGHT.name())
                                                            .stream()
                                                            .filter(m -> m.isBelonging(source.getSunlightEmission()))
                                                            .collect(toSet()))
                                     .fTempOut(memGradeService.findByProperty(TEMP_OUT.name())
                                                              .stream()
                                                              .filter(m -> m.isBelonging(source.getTempOut()))
                                                              .collect(toSet()))
                                     .fWindSpeed(
                                             memGradeService.findByProperty(WIND_SPEED.name())
                                                            .stream()
                                                            .filter(m -> m.isBelonging(source.getWindSpeed()))
                                                            .collect(toSet()))
                                     .weatherEvents(source.getWeatherEvents())
                                     .build();
    }
}
