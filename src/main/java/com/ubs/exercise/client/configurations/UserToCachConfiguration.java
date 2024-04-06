package com.ubs.exercise.client.configurations;

import com.ubs.exercise.client.fetcher.JsonFetcher;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.UserCsvMapper;
import com.ubs.exercise.client.repository.UserRepo;
import com.ubs.exercise.client.services.CSVToCacheService;
import com.ubs.exercise.client.services.JsonToCacheService;
import com.ubs.exercise.client.services.XmlToCacheService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserToCachConfiguration {
    @Bean
    public CSVToCacheService<User, UserRepo, UserCsvMapper> CSVToCacheService(){
        return new CSVToCacheService<User, UserRepo, UserCsvMapper>();
    }

    @Bean
    public JsonToCacheService<User, UserRepo> jsonToCacheService(){
        return new JsonToCacheService<User, UserRepo>(User.class);
    }

    @Bean
    public XmlToCacheService<User, UserRepo> xmlToCacheService(){
        return new XmlToCacheService<User, UserRepo>(User.class);
    }

    @Bean
    public JsonFetcher<String, UserRepo> jsonFetcher(){
        return new JsonFetcher<String, UserRepo>();
    }


}
