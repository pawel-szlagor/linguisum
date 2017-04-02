package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Field;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config.BasicMongoConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.SummaryProfile;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.SummaryProfileRepository;

/**
 * Created by Pawel on 2017-01-30.
 */
public class GenerateSummaryProfiles {

    public static void main(String[] args) {
        generateProfiles();
    }

    public static void generateProfiles() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicMongoConfig.class);
        SummaryProfileRepository repository = ctx.getBean(SummaryProfileRepository.class);
        for (Field field : SummaryProfiles.class.getFields()) {
            field.setAccessible(true);
        }
        repository.save(stream(SummaryProfiles.class.getFields()).map(f -> {
            try {
                return (SummaryProfile) f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(toList()));
    }
}
