package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems;

import java.io.FileNotFoundException;

/**
 * Created by Pawel on 2017-01-29.
 */
public interface WeatherConditionsImportService {

    void importAll() throws FileNotFoundException;
}
