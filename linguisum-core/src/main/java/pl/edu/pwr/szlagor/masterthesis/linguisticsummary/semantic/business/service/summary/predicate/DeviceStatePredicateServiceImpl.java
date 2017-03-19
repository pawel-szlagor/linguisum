package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot;
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
        return deviceRepository.findAll()
                               .stream()
                               .map(p -> QSnapshot.snapshot.deviceStates.contains(new DeviceState(p, true)))
                               .collect(toList());
    }
}
