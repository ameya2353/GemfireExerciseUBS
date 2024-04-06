package com.ubs.exercise.client.converter;


import java.util.List;

public interface IConverter{

    <T> List<T> convert(String path) throws Exception;

    public void setConverterStrategy(BaseMapper strategy) throws Exception;

    public boolean isFileValid(String filePath);

    public void supports(Class clazz) throws IllegalAccessException;

}
