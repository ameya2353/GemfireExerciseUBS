package com.ubs.exercise.persister;

import com.ubs.exercise.converter.IConverter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class Persister<T> {
    private CrudRepository repository;
    private List<T> convertedData;
    private IConverter converter;

    public Persister(CrudRepository repository ,  IConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    /*
    * convert data from giving file path to list of object
    * store it in the convertedData
    *
    * */
    public abstract void convert(String filePath);

    /*
     * set converted data list
     * @param convertedData list of converted elements
     *
     * */
    protected void setConverterData(List<T> convertedData){
        this.convertedData = convertedData;
    }

    /*
     * persist data to the database
     *
     * */
    public void savedata() {
        this.repository.saveAll(this.convertedData);
    }
}
