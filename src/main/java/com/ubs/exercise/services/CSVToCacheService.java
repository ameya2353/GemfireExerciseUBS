package com.ubs.exercise.services;

import com.ubs.exercise.converter.BaseMapper;
import com.ubs.exercise.converter.IConverter;
import com.ubs.exercise.converter.json.Mapper;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.persister.Persister;
import com.ubs.exercise.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
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
