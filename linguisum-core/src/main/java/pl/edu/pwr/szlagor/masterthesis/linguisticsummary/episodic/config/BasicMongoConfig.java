package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

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

}
