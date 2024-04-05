package com.ubs.exercise.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ubs.exercise.converter.json.JsonConverter;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.persister.Persister;
import org.apache.commons.beanutils.expression.Resolver;
import org.apache.commons.lang3.reflect.TypeLiteral;
import org.aspectj.weaver.ResolvedType;
import org.springframework.aop.support.AopUtils;
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
