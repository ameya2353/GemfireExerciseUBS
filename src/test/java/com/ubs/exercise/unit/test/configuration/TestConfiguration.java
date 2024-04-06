package com.ubs.exercise.unit.test.configuration;

import com.ubs.exercise.client.converter.csv.CSVConverter;
import com.ubs.exercise.client.converter.json.JsonConverter;
import com.ubs.exercise.client.converter.xml.XMLConverter;
import com.ubs.exercise.client.fetcher.JsonFetcher;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.UserCsvMapper;
import com.ubs.exercise.client.repository.UserRepo;
import com.ubs.exercise.client.services.CSVToCacheService;
import com.ubs.exercise.client.services.JsonToCacheService;
import com.ubs.exercise.client.services.XmlToCacheService;
import com.ubs.exercise.client.services.user.UserService;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@ClientCacheApplication
@EnableEntityDefinedRegions(basePackageClasses = User.class, clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories(basePackageClasses = UserRepo.class)
public class TestConfiguration {

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public CSVConverter csvConverter() {
        return new CSVConverter();
    }

    @Bean
    public UserCsvMapper csvMapper() {
        return new UserCsvMapper();
    }

    @Bean
    public CSVToCacheService CSVToCacheService() {
        return new CSVToCacheService<User, UserRepo, UserCsvMapper>();
    }

    @Bean
    public JsonConverter jsonConverter() {
        return new JsonConverter();
    }

    @Bean
    public JsonToCacheService JsonToCacheService() {
        JsonToCacheService service = new JsonToCacheService<User, UserRepo>(User.class);
        return service;
    }

    @Bean
    public XmlToCacheService xmlToCacheService() {
        XmlToCacheService service = new XmlToCacheService<User, UserRepo>(User.class);
        return service;
    }


    @Bean
    public XMLConverter xmlConverter() {
        return new XMLConverter();
    }

    @Bean
    public JsonFetcher<String, UserRepo> jsonFetcher() {
        return new JsonFetcher<String, UserRepo>();
    }

}