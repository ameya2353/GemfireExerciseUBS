package com.ubs.exercise.persister.csv;

import com.ubs.exercise.converter.IConverter;
import com.ubs.exercise.persister.Persister;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public class CsvPersister<T> extends Persister <T>{
    public CsvPersister(CrudRepository repository , IConverter converter) {
        super(repository , converter);
    }

    @Override
    public void convert(String filePath) {
        super.setConverterData(convertCsvToPersistanceObject());
    }


    private List<T> convertCsvToPersistanceObject(){

        return new ArrayList<T>();
    }
}
