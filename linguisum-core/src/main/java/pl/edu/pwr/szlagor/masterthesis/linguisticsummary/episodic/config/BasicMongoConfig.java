package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.converter.BooleanExpressionConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.converter.StringToBooleanExpressionConverter;

/**
 * Created by Pawel on 2017-01-16.
 */
@Configuration
@ComponentScan(value = { "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.*",
                         "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon.*" })
@EnableMongoRepositories(basePackages = { "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository",
                                          "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository" })
public class BasicMongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "iHouse";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic";
    }

    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new BooleanExpressionConverter());
        converterList.add(new StringToBooleanExpressionConverter());
        return new CustomConversions(converterList);
    }
}
