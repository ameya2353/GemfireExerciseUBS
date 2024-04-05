package com.ubs.exercise.fetcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubs.exercise.fetcher.exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class JsonFetcher< I , R extends CrudRepository> implements Ifetcher<String , I>{

    @Autowired
    R repository;

    @Override
    public String fetch(I id) throws Exception {
        Optional obj = repository.findById(id);
        if(obj.isEmpty()){
            throw new NoDataException("No Data Found");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj.get());
    }

    @Override
    public String fetchAll(List<I>  id) throws Exception {
        Iterable<I> res = repository.findAllById(id);
        if(StreamSupport.stream(res.spliterator(),false).count()==0){
            throw new NoDataException("No Data Found");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(res);
    }
}
