package com.ubs.exercise.services.user;

import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.CSVToCacheService;
import com.ubs.exercise.services.JsonToCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    CSVToCacheService<User , UserRepo ,UserCsvMapper> csvService;

    @Autowired
    JsonToCacheService<User , UserRepo> jsonService;

    public boolean persistCSVData(String path) throws Exception {
        return csvService.persistData(path);
    }

    public boolean persistJsonData(String path) throws Exception {
        return jsonService.persistJsonData(path);
    }

}
