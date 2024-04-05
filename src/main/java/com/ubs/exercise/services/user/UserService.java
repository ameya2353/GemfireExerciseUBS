package com.ubs.exercise.services.user;

import com.ubs.exercise.fetcher.JsonFetcher;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.CSVToCacheService;
import com.ubs.exercise.services.JsonToCacheService;
import com.ubs.exercise.services.XmlToCacheService;
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
        return userJsonFetcher.fetch(id);
    }

    public String fetchAllUserDataJson(List<String> ids) throws Exception {
        return userJsonFetcher.fetchAll(ids);
    }

}
