package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.converter;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.MediaUsage;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PDevicesStates;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PMediaUsages;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PRoomsStates;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PersonRepository;

/**
 * Created by Pawel on 2017-03-23.
 */
@Component
public class PSnapshotConverter implements Converter<Snapshot, PSnapshot> {

    private Person person;

    @Autowired
    public PSnapshotConverter(PersonRepository personRepository) {
        person = personRepository.findOne(1L);
    }

    @Override
    public PSnapshot convert(Snapshot source) {
        return PSnapshot.builder()
                        .time(source.getTime().getHour() * 10000 + source.getTime().getMinute() * 100 + source.getTime().getSecond())
                        .date(source.getDate().getMonthValue() * 100 + source.getDate().getDayOfMonth())
                        .dayOfWeek(source.getDate().getDayOfWeek().getValue())
                        .person(person)
                        .deviceStates(transformDeviceStates(source))
                        .id(source.getId())
                        .mediaUsages(transformMediaUsages(source))
                        .personStates(source.getPersonStates().isEmpty() ? null : source.getPersonStates().iterator().next())
                        .roomStates(transformRoomStates(source))
                        .weatherConditions(source.getWeatherConditions())
                        .build();
    }

    private PDevicesStates transformDeviceStates(Snapshot source) {
        final List<DeviceState> deviceStates = Lists.newArrayList(source.getDeviceStates());
        deviceStates.sort(Comparator.comparingLong(d -> d.getDevice().getId()));
        final int size = deviceStates.size();
        if (deviceStates.size() < 10) {
            for (int i = 0; i < 10 - size; i++)
                deviceStates.add(null);
        }
        return PDevicesStates.builder()
                             .deviceState1(deviceStates.get(0))
                             .deviceState2(deviceStates.get(1))
                             .deviceState3(deviceStates.get(2))
                             .deviceState4(deviceStates.get(3))
                             .deviceState5(deviceStates.get(4))
                             .deviceState6(deviceStates.get(5))
                             .deviceState7(deviceStates.get(6))
                             .deviceState8(deviceStates.get(7))
                             .deviceState9(deviceStates.get(8))
                             .deviceState10(deviceStates.get(9))
                             .build();

    }

    private PMediaUsages transformMediaUsages(Snapshot source) {
        final List<MediaUsage> mediaUsages = Lists.newArrayList(source.getMediaUsages());
        mediaUsages.sort(Comparator.comparingInt(d -> d.getMediaType().ordinal()));
        final int size = mediaUsages.size();
        if (mediaUsages.size() < 10) {
            for (int i = 0; i < 10 - size; i++)
                mediaUsages.add(null);
        }
        return PMediaUsages.builder()
                           .mediaUsage1(mediaUsages.get(0))
                           .mediaUsage2(mediaUsages.get(1))
                           .mediaUsage3(mediaUsages.get(2))
                           .mediaUsage4(mediaUsages.get(3))
                           .build();
    }

    private PRoomsStates transformRoomStates(Snapshot source) {
        final List<RoomState> RoomStates = Lists.newArrayList(source.getRoomStates());
        RoomStates.sort(Comparator.comparingLong(d -> d.getRoom().getId()));
        final int size = RoomStates.size();
        if (RoomStates.size() < 10) {
            for (int i = 0; i < 10 - size; i++)
                RoomStates.add(null);
        }
        return PRoomsStates.builder()
                           .roomState1(RoomStates.get(0))
                           .roomState2(RoomStates.get(1))
                           .roomState3(RoomStates.get(2))
                           .roomState4(RoomStates.get(3))
                           .roomState5(RoomStates.get(4))
                           .roomState6(RoomStates.get(5))
                           .roomState7(RoomStates.get(6))
                           .roomState8(RoomStates.get(7))
                           .roomState9(RoomStates.get(8))
                           .roomState10(RoomStates.get(9))
                           .build();
    }

}
