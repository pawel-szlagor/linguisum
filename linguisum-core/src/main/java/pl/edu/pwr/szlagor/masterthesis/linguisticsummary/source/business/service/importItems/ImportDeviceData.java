package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.DeviceSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.BasicMySQLConfig;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Pawel on 2017-01-30.
 */
public class ImportDeviceData {

    public static void main(String[] args) throws FileNotFoundException {
        importData();
    }

    public static void importData() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicMySQLConfig.class);
        DeviceSourceService service = ctx.getBean(DeviceSourceService.class);
        Field[] fields = SampleDevices.class.getFields();
        List<Field> fieldsList = Arrays.stream(fields).filter(field -> field.getType().equals(DeviceSourceDto.class)).collect(toList());
        for (Field field : fieldsList) {
            field.setAccessible(true);
        }
        service.save(fieldsList.stream().map(f -> {
            try {
                return (DeviceSourceDto) f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()));
    }
}
