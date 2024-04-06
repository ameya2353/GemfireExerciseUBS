package com.ubs.exercise.client.converter.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubs.exercise.client.converter.BaseMapper;
import com.ubs.exercise.client.converter.IConverter;
import com.ubs.exercise.client.converter.json.exception.InvalidJsonFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Slf4j
@Component("jsonConverter")
public class JsonConverter implements IConverter {

    Class clazz =null;

    @Override
    public <T> List<T> convert(String path) throws IOException {
        if(path==null){
            log.info("No path specified.");
            return Collections.EMPTY_LIST;
        }
        isFileValid(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path),mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    @Override
    public void setConverterStrategy(BaseMapper strategy) throws Exception {
       throw new IllegalAccessException("Json Converter does not require Converter Strategy. Please remove the setConverterStrategy function call");
    }

    @Override
    public boolean isFileValid(String filePath) {
        if(filePath.endsWith(".json")){
            return true;
        }
        throw new InvalidJsonFileException("Provided file is not a json file");
    }

    @Override
    public void supports(Class clazz) throws IllegalAccessException {
        this.clazz = clazz;
    }
}