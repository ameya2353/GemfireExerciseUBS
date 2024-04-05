package com.ubs.exercise.persister;

import com.ubs.exercise.converter.IConverter;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Persister<T> {
    private CrudRepository repository;
    private List<T> convertedData;

    public Persister(@NonNull CrudRepository repository ,@NonNull List<T> convertedData) {
        if(repository == null){
            throw new NullPointerException("Repository cannot be null");
        }
        this.repository = repository;
        this.convertedData = convertedData;
    }

    public boolean savedata() {
        if(!(this.convertedData.isEmpty() || this.convertedData == null)){
            return StreamSupport.stream(this.repository.saveAll(this.convertedData).spliterator(),false).count()>0L;
        }
       return false;
    }

    public void destroy(){
        this.convertedData = null;
    }
}
