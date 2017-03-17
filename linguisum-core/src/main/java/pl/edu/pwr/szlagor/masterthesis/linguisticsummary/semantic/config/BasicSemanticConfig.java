package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.config;

/**
 * Created by Pawel on 2017-03-12.
 */

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by Pawel on 2017-01-16.
 */
@EnableCaching
@Configuration
@PropertySource(value = {"classpath:application.properties"})
@EnableTransactionManagement
@ComponentScan("pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.*")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic")
public class BasicSemanticConfig {
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.semantic.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HBM2DDL_AUTO = "semantic.hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    private static final String HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    private static final String HIBERNATE_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";
    private static final String HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(hibProperties());
        entityManagerFactoryBean.setPersistenceUnitName("semanticMySQL");
        entityManagerFactoryBean.setPackagesToScan("pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic", "pl" +
                ".edu.pwr.szlagor.masterthesis.linguisticsummary.common");
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put(PROPERTY_NAME_HBM2DDL_AUTO, env.getRequiredProperty(PROPERTY_NAME_HBM2DDL_AUTO));
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(HIBERNATE_USE_SECOND_LEVEL_CACHE, true);
        properties.put(HIBERNATE_CACHE_USE_QUERY_CACHE, true);
        properties.put(HIBERNATE_CACHE_REGION_FACTORY_CLASS, env.getRequiredProperty(HIBERNATE_CACHE_REGION_FACTORY_CLASS));
        properties.put(HIBERNATE_JDBC_FETCH_SIZE, env.getRequiredProperty(HIBERNATE_JDBC_FETCH_SIZE));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public CacheManager getCacheManager() {
        return new EhCacheCacheManager(getEhCacheFactory().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean getEhCacheFactory() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setShared(true);
        return factoryBean;
    }

}