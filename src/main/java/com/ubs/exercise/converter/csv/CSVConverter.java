package com.ubs.exercise.converter.csv;

import com.fasterxml.jackson.core.type.TypeReference;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.ubs.exercise.converter.IConverter;
import com.ubs.exercise.converter.exception.CsvConverterException;
import com.ubs.exercise.converter.exception.InvalidCsvFileException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CSVConverter implements IConverter {
    private @Setter ColumnPositionMappingStrategy converter;

    @Override
    public <T> List<T> convert(String path , Class<T> type) throws IOException {
        if(path==null){
            log.info("No path specified.");
            return Collections.EMPTY_LIST;
        }

        if(!path.endsWith(".csv")){
            throw new InvalidCsvFileException("Provided file is not a csv file");
        }
        if(converter==null){
            throw new CsvConverterException("Converter Mapping not provided , set the converter mapping");
        }
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(new TypeReference<T>(){}.getClass())
                    .withIgnoreLeadingWhiteSpace(true)
                    .withVerifyReader(true)
                    .withMappingStrategy(converter)
                    .build();
        return csvToBean.parse();
    }
}
