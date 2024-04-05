package com.ubs.exercise.converter.xml;

import com.ubs.exercise.converter.BaseMapper;
import com.ubs.exercise.converter.IConverter;

import java.io.IOException;
import java.util.List;

public class XMLConverter implements IConverter {
    @Override
    public <T> List<T> convert(String path, Class<T> type) throws Exception {
        return null;
    }

    @Override
    public <T> List<T> convert(String path) throws IOException {
        return null;
    }

    @Override
    public void setConverterStrategy(BaseMapper strategy) {

    }

    @Override
    public boolean isFileValid(String filePath) {
        return false;
    }
}
