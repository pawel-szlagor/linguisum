package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.impl;

import java.util.Map;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySchemaSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.MediaUsageSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.ActivitySchemaService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.distribution.NormalDistributionGenerator;

/**
 * Created by Pawel on 2017-02-02.
 */
@Service
public class ActivitySchemaServiceImpl implements ActivitySchemaService {

    private static final double SIGMA = 0.1;
    private static final double MIN_DURATION = 0.15;
    private final NormalDistributionGenerator random;

    @Autowired
    public ActivitySchemaServiceImpl(NormalDistributionGenerator random) {
        this.random = random;
    }

    @Override
    public ActivitySchemaSourceDto randomizeDaily(ActivitySchemaSourceDto activitySchema) {
        activitySchema.getLocationProbabilities().replaceAll((r, l) -> random.randomValueInBorders(l, l * SIGMA, l * SIGMA));
        activitySchema.getLocationProbabilities().entrySet().removeIf(r -> r.getValue() < MIN_DURATION);
        activitySchema.getLocationProbabilities().replaceAll((r, l) -> l * 1 / activitySchema.getLocationProbabilities().entrySet().stream().mapToDouble(Map.Entry::getValue).sum());
        for (MediaUsageSourceDto usages : activitySchema.getMediaUsageSourceDtos()) {
            double initial = usages.getUsagePerMinute();
            usages.setUsagePerMinute(random.randomValueInBorders(initial, initial * SIGMA, initial * SIGMA));
        }
        activitySchema.getMediaUsageSourceDtos().removeIf(r -> r.getUsagePerMinute() < MIN_DURATION);
        activitySchema.getPreferredTempInLocations().entrySet().forEach(l -> l.setValue(Precision.round(random.randomValueInBorders(l.getValue(), 0.5, 0.5), 1)));
        activitySchema.getPreferredTempInLocations().entrySet().removeIf(r -> !activitySchema.getLocationProbabilities().containsKey(r.getKey()));
        activitySchema.getTimeOfDeviceUsages().entrySet().forEach(l -> l.setValue(random.randomValueInBorders(l.getValue(), l.getValue() * SIGMA, l.getValue() * SIGMA)));
        activitySchema.getTimeOfDeviceUsages().entrySet().removeIf(r -> r.getValue() < MIN_DURATION);
        activitySchema.getLocationProbabilities().replaceAll((r, l) -> l * 1 / activitySchema.getLocationProbabilities().entrySet().stream().mapToDouble(Map.Entry::getValue).sum());
        return activitySchema;
    }

}
