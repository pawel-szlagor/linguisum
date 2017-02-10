package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems;

import static java.util.stream.Collectors.toList;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person.PersonSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person.SamplePersons;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.BasicMySQLConfig;

/**
 * Created by Pawel on 2017-01-30.
 */
public class ImportPersonsData {

    public static void main(String[] args) throws FileNotFoundException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicMySQLConfig.class);
        PersonSourceService service = ctx.getBean(PersonSourceService.class);
        Field[] fields = SamplePersons.class.getFields();
        List<Field> fieldsList = Arrays.stream(fields).filter(field -> field.getType().equals(PersonSourceDto.class)).collect(toList());
        for (Field field : fieldsList) {
            field.setAccessible(true);
        }
        service.save(fieldsList.stream().map(f -> {
            try {
                return (PersonSourceDto) f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()));
    }
}
