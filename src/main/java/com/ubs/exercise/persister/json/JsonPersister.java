package com.ubs.exercise.persister.json;

import com.ubs.exercise.converter.IConverter;
import com.ubs.exercise.persister.Persister;
import org.springframework.data.repository.CrudRepository;

public class JsonPersister extends Persister {
    public JsonPersister(CrudRepository repository , IConverter converter) {
        super(repository , converter);
    }

    @Override
    public void convert(String filePath) {

    }
}
