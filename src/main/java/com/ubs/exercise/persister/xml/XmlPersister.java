package com.ubs.exercise.persister.xml;

import com.ubs.exercise.converter.IConverter;
import com.ubs.exercise.persister.Persister;
import org.springframework.data.repository.CrudRepository;

public class XmlPersister<T> extends Persister<T> {

    public XmlPersister(CrudRepository repository , IConverter converter) {
        super(repository , converter);
    }

    @Override
    public void convert(String filePath) {

    }

}
