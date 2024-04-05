package com.ubs.exercise.configurations;

import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.CSVToCacheService;
import com.ubs.exercise.services.JsonToCacheService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanToCachConfiguration {
    @Bean
    public CSVToCacheService<User, UserRepo, UserCsvMapper> CSVToCacheService(){
        return new CSVToCacheService<User, UserRepo, UserCsvMapper>();
    }

    @Bean
    public JsonToCacheService jsonToCacheService(){
        return new JsonToCacheService<User, UserRepo>();
    }

}
