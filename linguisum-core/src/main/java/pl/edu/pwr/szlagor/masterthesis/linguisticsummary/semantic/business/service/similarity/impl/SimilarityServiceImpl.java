package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.similarity.impl;

import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.EnvironmentConditionsDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.SnapshotDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.similarity.SimilarityService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.MemGradeService;

/**
 * Created by Pawel on 2017-03-12.
 */
@Service
public class SimilarityServiceImpl implements SimilarityService {
    private final MemGradeService memGradeService;

    @Autowired
    public SimilarityServiceImpl(MemGradeService memGradeService) {
        this.memGradeService = memGradeService;
    }

    @Override
    public double calculateSimilarity(SnapshotDto one, SnapshotDto other) {
        return calculateDistanceWeather(one.getWeatherConditions(), other.getWeatherConditions());
    }

    private double calculateDistanceWeather(EnvironmentConditionsDto one, EnvironmentConditionsDto other) {
        return DoubleStream.of(memGradeService.calculateDistance(one.getTempOut(), other.getTempOut(), "tempOut"), memGradeService.calculateDistance(one.getWindSpeed(), other.getWindSpeed(), "windSpeed"), memGradeService.calculateDistance(one.getHumidity(), other.getHumidity(), "humidity"), memGradeService.calculateDistance((double) one.getPressure(), (double) other.getPressure(), "pressure"), memGradeService.calculateDistance(one.getPrecipitation(), other.getPrecipitation(), "precipitation")).average().getAsDouble();
    }
}
