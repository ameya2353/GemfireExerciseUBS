package com.ubs.exercise.client.services;

import com.ubs.exercise.client.converter.json.JsonConverter;
import com.ubs.exercise.client.persister.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class JsonToCacheService<T , R extends CrudRepository> implements IDataToCacheService{
    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    private R repository;

    private Class clazz;
    public JsonToCacheService(Class clazz){
        this.clazz = clazz;

    }

    @Override
    public boolean persistData(String path) throws Exception {
        jsonConverter.supports(clazz);
        return new Persister<T>(repository , jsonConverter.convert(path)).savedata();
    }
}
