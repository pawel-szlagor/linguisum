package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.util.stream.Collectors.toList;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.MemGradeService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.config.BasicSemanticConfig;

/**
 * Created by Pawel on 2017-01-30.
 */
public class ImportMemGradeData {

    public static void main(String[] args) {
        importData();
    }

    public static void importData() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicSemanticConfig.class, BasicMongoConfig.class);
        MemGradeService service = ctx.getBean(MemGradeService.class);
        final List<Field> fields = Stream.of(ElectricityUsageLevels.class.getFields(),
                HumidityLevels.class.getFields(),
                PrecipitationLevels.class.getFields(),
                PressureLevels.class.getFields(),
                SunlightLevels.class.getFields(),
                TempInLevels.class.getFields(),
                TempOutLevels.class.getFields(),
                ColdWaterUsageLevels.class.getFields(),
                HotWaterUsageLevels.class.getFields(),
                DayPhaseLevels.class.getFields(),
                WindSpeedLevels.class.getFields()).flatMap(Arrays::stream).collect(toList());
        for (Field field : fields) {
            field.setAccessible(true);
        }
        service.save(fields.stream().map(f -> {
            try {
                return (TrapezoidalMemGradeDto) f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()));
    }
}
