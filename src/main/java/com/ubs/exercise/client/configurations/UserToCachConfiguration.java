package com.ubs.exercise.client.configurations;

import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.UserCsvMapper;
import com.ubs.exercise.client.repository.UserRepo;
import org.springframework.context.annotation.Configuration;


/*
* Configuration class for configuring the Bean to Cache operation
* for easier creation the base type is ToCachConfiguration class it is a generic class.
* to create new bean to cache config extend ToCachConfiguration and specify all the parameters.
* */

@Configuration
public class UserToCachConfiguration extends  ToCachConfiguration<String , UserRepo , UserRepo , UserCsvMapper>{
    UserToCachConfiguration(){
        clazz = User.class;
    }

}
