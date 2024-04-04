package com.ubs.exercise.model.user;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.exceptions.*;
import com.ubs.exercise.model.user.address.Address;

public class UserCsvMapper extends ColumnPositionMappingStrategy {
    public UserCsvMapper() {
        this.setType(User.class);
    }

    @Override
    public Object populateNewBean(String[] line) throws CsvBeanIntrospectionException, CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException, CsvConstraintViolationException, CsvValidationException {
        Address address = new Address(line[2] , line[3] ,line[4] );
        User person = new User(line[0].trim() , line[1] ,address);
        return person;
    }
}
