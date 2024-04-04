package com.ubs.exercise.configurations;

import com.ubs.exercise.model.user.User;
import org.apache.geode.cache.GemFireCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.util.Properties;

@Configuration
@EnableGemfireRepositories(basePackages = {"com.ubs.exercise.repository"})
public class GemfireConfiguration {

    @Bean
    Properties gemfireProperties() {
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name","SpringDataGemFireApplication");
        gemfireProperties.setProperty("mcast-port", "0");
        gemfireProperties.setProperty("log-level", "config");
        return gemfireProperties;
    }

    @Bean
    CacheFactoryBean gemfireCache() {
        CacheFactoryBean gemfireCache = new CacheFactoryBean();
        gemfireCache.setClose(true);
        gemfireCache.setProperties(gemfireProperties());
        return gemfireCache;
    }

    @Bean(name="user")
    LocalRegionFactoryBean<String, User> getEmployee(final GemFireCache cache) {
        LocalRegionFactoryBean<String, User> employeeRegion = new LocalRegionFactoryBean();
        employeeRegion.setCache(cache);
        employeeRegion.setName("user");
        return employeeRegion;
    }
}