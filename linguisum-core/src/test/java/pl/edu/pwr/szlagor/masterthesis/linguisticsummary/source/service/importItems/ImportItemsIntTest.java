package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.service.importItems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems
        .WeatherConditionsImportService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.TestMySQLConfig;

import java.io.FileNotFoundException;

/**
 * Created by Pawel on 2017-01-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestMySQLConfig.class, loader = AnnotationConfigContextLoader.class)
public class ImportItemsIntTest {

    @Autowired
    private WeatherConditionsImportService weatherConditionsImportService;

    @Test
    public void shouldImportAllItems() throws FileNotFoundException {
        weatherConditionsImportService.importAll();
    }
}
