package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Pawel on 2017-01-16.
 */
@Configuration
@EnableMongoRepositories(basePackages = "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.repository")
public class TestMongoConfig extends AbstractMongoConfiguration {

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
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary";
    }


}
