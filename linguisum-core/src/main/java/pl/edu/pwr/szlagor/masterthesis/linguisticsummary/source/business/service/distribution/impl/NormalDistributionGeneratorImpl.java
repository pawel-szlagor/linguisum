package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.distribution.impl;

import java.util.Random;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.distribution.NormalDistributionGenerator;

@Service
public class NormalDistributionGeneratorImpl implements NormalDistributionGenerator {

    private final Random random = new Random();

    @Override
    public double randomValueInBorders(double mean, double upperLimit, double downLimit) {
        NormalDistribution distribution = new NormalDistribution(mean, Math.max(upperLimit, downLimit) / 3);
        double result = distribution.sample();
        while (result < mean - downLimit || result > mean + upperLimit) {
            result = distribution.sample();
        }
        return result;
    }

    @Override
    public long nextInt(int max) {
        return new Random().nextInt(max);
    }
}