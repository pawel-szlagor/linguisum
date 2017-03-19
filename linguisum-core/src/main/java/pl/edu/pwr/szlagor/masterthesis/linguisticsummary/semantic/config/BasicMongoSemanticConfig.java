package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Created by Pawel on 2017-01-16.
 */
// @Configuration
@ComponentScan(value = "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.*",
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@EnableMongoRepositories(basePackages = "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository")
public class BasicMongoSemanticConfig extends AbstractMongoConfiguration {

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
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary";
    }

}
