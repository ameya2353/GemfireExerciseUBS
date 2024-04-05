package com.ubs.exercise.configurations;

import com.ubs.exercise.fetcher.JsonFetcher;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.CSVToCacheService;
import com.ubs.exercise.services.JsonToCacheService;
import com.ubs.exercise.services.XmlToCacheService;
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
