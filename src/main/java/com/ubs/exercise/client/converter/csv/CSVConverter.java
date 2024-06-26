package com.ubs.exercise.client.converter.csv;

import com.fasterxml.jackson.core.type.TypeReference;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.ubs.exercise.client.converter.BaseMapper;
import com.ubs.exercise.client.converter.IConverter;
import com.ubs.exercise.client.converter.IConverterWithStrategy;
import com.ubs.exercise.client.converter.csv.exception.InvalidCsvFileException;
import com.ubs.exercise.client.converter.csv.exception.CsvConverterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/*
*
* This class is responsible to handle the conversion of CSV files to POJO objects.
*
* */

@Slf4j
@Component("csvConverter")
public class CSVConverter<T> implements IConverterWithStrategy {
    private ColumnPositionMappingStrategy converterStrategy;



    @Override
    public <T> List<T> convert(String path) throws Exception {
        if(path==null){
            log.info("No path specified, returning empty list");
            return Collections.EMPTY_LIST;
        }
        isFileValid(path);
        if(converterStrategy==null){
            throw new CsvConverterException("Converter Mapping not provided , set the converter mapping");
        }
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(new TypeReference<T>(){}.getClass())
                    .withIgnoreLeadingWhiteSpace(true)
                    .withVerifyReader(true)
                    .withMappingStrategy(converterStrategy)
                    .build();
        return csvToBean.parse();
    }

    @Override
    public void setConverterStrategy(BaseMapper strategy) {
        converterStrategy = (ColumnPositionMappingStrategy) strategy;
    }

    @Override
    public boolean isFileValid(String filePath) throws Exception {
        if(filePath.endsWith(".csv") && isFileExists(filePath)){
            return true;
        }
        throw new InvalidCsvFileException("Provided file is not a csv file");
    }

    @Override
    public void supports(Class clazz) throws IllegalAccessException {
        throw new IllegalAccessException("Supports not required");
    }
}
