package com.ubs.exercise.model.user;

import com.opencsv.exceptions.*;
import com.ubs.exercise.converter.csv.Mapper;
import com.ubs.exercise.model.user.address.Address;
import org.springframework.stereotype.Component;

@Component
public class UserCsvMapper extends Mapper {
    public UserCsvMapper() {
        this.setType(User.class);
    }

    @Override
    public Object populateNewBean(String[] line) throws CsvBeanIntrospectionException{
        Address address = new Address(line[2] , line[3] ,line[4] );
        User person = new User(line[0].trim() , line[1] ,address);
        return person;
    }
}
