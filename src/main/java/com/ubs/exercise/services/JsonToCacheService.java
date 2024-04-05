package com.ubs.exercise.services;

import com.ubs.exercise.converter.json.JsonConverter;
import com.ubs.exercise.persister.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonToCacheService<T , R extends CrudRepository>{
    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    private R repository;


    public boolean persistJsonData(String path) throws Exception {
        return new Persister<T>(repository , jsonConverter.convert(path)).savedata();
    }
}
