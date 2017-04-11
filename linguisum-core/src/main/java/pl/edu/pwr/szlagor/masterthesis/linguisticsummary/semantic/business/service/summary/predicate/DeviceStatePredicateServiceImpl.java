package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QPSnapshot.pSnapshot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.SimplePath;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Device;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.DeviceRepository;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service(value = "deviceStatePredicateService")
public class DeviceStatePredicateServiceImpl implements CategoryPredicateService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceStatePredicateServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<BooleanExpression> createPossiblePredicates() {
        final List<Device> deviceList = deviceRepository.findAll();
        deviceList.sort(Comparator.comparingLong(Device::getId));
        final List<SimplePath<DeviceState>> simplePaths = Lists.newArrayList(pSnapshot.deviceStates.deviceState1,
                pSnapshot.deviceStates.deviceState2,
                pSnapshot.deviceStates.deviceState3,
                pSnapshot.deviceStates.deviceState4,
                pSnapshot.deviceStates.deviceState5,
                pSnapshot.deviceStates.deviceState6,
                pSnapshot.deviceStates.deviceState7,
                pSnapshot.deviceStates.deviceState8,
                pSnapshot.deviceStates.deviceState9,
                pSnapshot.deviceStates.deviceState10);
        simplePaths.subList(0, deviceList.size());
        return IntStream.range(0, deviceList.size())
                        .mapToObj(i -> simplePaths.get(i).eq(DeviceState.builder().device(deviceList.get(i)).isOn(true).build()))
                        .collect(toList());
    }
}
