package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.distribution;

/**
 * Created by Pawel on 2017-02-02.
 */
public interface NormalDistributionGenerator {
    double randomValueInBorders(double mean, double upperLimit, double downLimit);

    long nextInt(int max);
}
