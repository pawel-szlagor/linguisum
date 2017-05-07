package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.QuantificatorLinguisticDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.quantificator.QuantificatorLinguisticService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.config.BasicSemanticConfig;

/**
 * Created by Pawel on 2017-01-30.
 */
public class ImportQuantificatorLinguisticData {

    public static void main(String[] args) {
        importData();
    }

    public static void importData() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicSemanticConfig.class, BasicMongoConfig.class);
        QuantificatorLinguisticService service = ctx.getBean(QuantificatorLinguisticService.class);
        final List<Field> fields = Arrays.asList(QuantificatorLinguisticData.class.getFields());
        for (Field field : fields) {
            field.setAccessible(true);
        }
        service.save(fields.stream().map(f -> {
            try {
                return (QuantificatorLinguisticDto) f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()));
    }
}
