package com.ubs.exercise.client.services.user;

import com.ubs.exercise.client.fetcher.JsonFetcher;
import com.ubs.exercise.client.function.executions.UserFunctionExecution;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.UserCsvMapper;
import com.ubs.exercise.client.repository.UserRepo;
import com.ubs.exercise.client.services.XmlToCacheService;
import com.ubs.exercise.client.services.CSVToCacheService;
import com.ubs.exercise.client.services.JsonToCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private CSVToCacheService<User , UserRepo ,UserCsvMapper> csvService;

    @Autowired
    private JsonToCacheService<User , UserRepo> jsonService;

    @Autowired
    private XmlToCacheService<User , UserRepo> xmlService;

    @Autowired
    private JsonFetcher<String , UserRepo> userJsonFetcher;

    @Autowired
    private UserFunctionExecution userFunctionExecution;

    public boolean persistCSVData(String path) throws Exception {
        return csvService.persistData(path);
    }

    public boolean persistJsonData(String path) throws Exception {
        return jsonService.persistData(path);
    }

    public boolean persistXmlData(String path) throws Exception {
        return xmlService.persistData(path);
    }

    public String fetchUserDataJson(String id) throws Exception {
        return userFunctionExecution.getUser(id);
    }

    public String fetchAllUserDataJson(List<String> ids) throws Exception {
        return userFunctionExecution.getAllUsers();
    }

}
