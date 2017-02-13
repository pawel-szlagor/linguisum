package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.BasicMySQLConfig;

import java.io.FileNotFoundException;

/**
 * Created by Pawel on 2017-01-30.
 */
public class ImportWeatherData {

    public static void main(String[] args) throws FileNotFoundException {
        importData();
    }

    public static void importData() throws FileNotFoundException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicMySQLConfig.class);
        WeatherConditionsImportService importService = ctx.getBean(WeatherConditionsImportService.class);
        importService.importAll();
    }
}
