package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems;

import static java.util.stream.Collectors.toList;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.RoomSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.RoomSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.BasicMySQLConfig;

/**
 * Created by Pawel on 2017-01-30.
 */
public class ImportRoomsData {

    public static void main(String[] args) throws FileNotFoundException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicMySQLConfig.class);
        RoomSourceService service = ctx.getBean(RoomSourceService.class);
        Field[] fields = SampleRooms.class.getFields();
        List<Field> fieldsList = Arrays.stream(fields).filter(field -> field.getType().equals(RoomSourceDto.class)).collect(toList());
        for (Field field : fieldsList) {
            field.setAccessible(true);
        }
        service.save(fieldsList.stream().map(f -> {
            try {
                return (RoomSourceDto) f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()));
    }
}
