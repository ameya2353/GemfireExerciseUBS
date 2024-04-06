package com.ubs.exercise.client.persister;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.StreamSupport;
/*
 * This class is responsible to persist the POJO to the cache on the server
 * Generic class T ->  class to be persisted
 * */
public class Persister<T> {
    private CrudRepository repository;
    private List<T> convertedData;

    /*
     * @param repository Crud repository to which the data is to be persisted.
     * @param convertedData List of data that is converted from the files.
     *
     * */
    public Persister(@NonNull CrudRepository repository ,@NonNull List<T> convertedData) {
        if(repository == null){
            throw new NullPointerException("Repository cannot be null");
        }
        this.repository = repository;
        this.convertedData = convertedData;
    }


    /*
     * Saves the given data into the cache.
     * */
    public boolean savedata() {
        if(!(this.convertedData.isEmpty() || this.convertedData == null)){
            return StreamSupport.stream(this.repository.saveAll(this.convertedData).spliterator(),false).count()>0L;
        }
       return false;
    }

    /*
     * destroy the converted data list to store new data is needed.
     *
     * */
    public void destroy(){
        this.convertedData = null;
    }
}
