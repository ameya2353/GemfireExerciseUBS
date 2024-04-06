package com.ubs.exercise.client.services.user;

import com.ubs.exercise.client.fetcher.JsonFetcher;
import com.ubs.exercise.client.function.executions.UserFunctionExecution;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.UserCsvMapper;
import com.ubs.exercise.client.repository.UserRepo;
import com.ubs.exercise.client.services.IDataToCacheService;
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
        return persistData(path , csvService);
    }

    public boolean persistJsonData(String path) throws Exception {
        return persistData(path , jsonService);
    }

    public boolean persistXmlData(String path) throws Exception {
        return persistData(path , xmlService);
    }

    public String fetchUserDataJson(String id) throws Exception {
        return  userFunctionExecution.getUser(id);
    }

    public String fetchAllUserDataJson(List<String> ids) throws Exception {
        return userFunctionExecution.getAllUsers();
    }


    private boolean persistData(String path , IDataToCacheService iDataToCacheService) throws Exception {
        return iDataToCacheService.persistData(path);
    }

}
