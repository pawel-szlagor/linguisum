package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems.impl;

import static java.lang.Double.parseDouble;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.WeatherConditionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.importItems.WeatherConditionsImportService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.WeatherConditionsService;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Setter
@NoArgsConstructor
@Service
public class WeatherConditionsImportServiceImpl implements WeatherConditionsImportService {

    private static final String INPUT_FILE_PATH = "E:\\Dysk Google\\Studia\\10 semestr\\Praca dyplomowa\\dane " +
            "meteo\\dane meteo.txt";

    private WeatherConditionsService weatherConditionsService;

    @Autowired
    public WeatherConditionsImportServiceImpl(WeatherConditionsService weatherConditionsService) {
        this.weatherConditionsService = weatherConditionsService;
    }

    @Override
    public void importAll() throws FileNotFoundException {
        Scanner docu = new Scanner(new FileReader(INPUT_FILE_PATH));
        List<WeatherConditionSourceDto> importedWeathers = Lists.newArrayListWithExpectedSize(365 * 24);
        docu.nextLine();
        for (String line = docu.nextLine(); docu.hasNextLine(); line = docu.nextLine()) {
            Scanner in = new Scanner(line.replaceAll("\\,", " "));
            WeatherConditionSourceDto.WeatherConditionSourceDtoBuilder builder = WeatherConditionSourceDto.builder();
            LocalDate date = LocalDate.of(2016, in.nextInt(), in.nextInt());
            LocalTime time = LocalTime.of(in.nextInt(), 0);
            builder.observationTime(LocalDateTime.of(date, time)).tempOut(parseDouble(in.next())).humidity(in.nextInt
                    ()).pressure(in.nextInt()).windSpeed(parseDouble(in.next())).sunlightEmission(parseDouble(in.next
                    ())).precipitation(parseDouble(in.next()));
            while (in.hasNext()) {
                builder.weatherEvent(WeatherEvent.getByKey(in.next()));
            }
            importedWeathers.add(builder.build());
        }
        weatherConditionsService.saveInBulk(importedWeathers);
    }


}
