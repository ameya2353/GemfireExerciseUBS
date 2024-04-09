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

/*
 *
 * This class is responsible to handle the conversion of JSON files to POJO objects.
 *
 * */
@Slf4j
@Component("jsonConverter")
public class JsonConverter implements IConverter {

    Class clazz =null;

    @Override
    public <T> List<T> convert(String path) throws Exception {
        if(path==null){
            log.info("No path specified.");
            return Collections.EMPTY_LIST;
        }
        isFileValid(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path),mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }


    @Override
    public boolean isFileValid(String filePath) throws Exception {
        if(filePath.endsWith(".json") && isFileExists(filePath)){
            return true;
        }
        throw new InvalidJsonFileException("Provided file is not a json file");
    }

    @Override
    public void supports(Class clazz) throws IllegalAccessException {
        this.clazz = clazz;
    }
}
