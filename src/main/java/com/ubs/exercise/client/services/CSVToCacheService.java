package com.ubs.exercise.client.services;

import com.ubs.exercise.client.converter.BaseMapper;
import com.ubs.exercise.client.converter.IConverter;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.persister.Persister;
import com.ubs.exercise.client.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


/*
* CSV to cache service
* this service is responsible to convert the CSV data and save it in the cache.
* */
public class CSVToCacheService<T , R extends CrudRepository , M extends BaseMapper> implements IDataToCacheService {
    @Autowired
    @Qualifier("csvConverter")
    private IConverter csvConverter;

    @Autowired
    private UserRepo repository;

    @Autowired
    private M mapper;


    @Override
    public boolean persistData(String path) throws Exception {
        csvConverter.setConverterStrategy(mapper);
        return new Persister<User>(repository , csvConverter.convert(path)).savedata();
    }
}
