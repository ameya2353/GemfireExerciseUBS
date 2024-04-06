package com.ubs.exercise.client.converter;


import java.util.List;

/*
* Interface all converters have to implement/
* 
* */

public interface IConverter{

    /*
     * responsible for conversion of the given data source to list of specified Type
     * @param path -> path of the file to be converted
     * returns List<T> -> list of type T
     * */
    <T> List<T> convert(String path) throws Exception;


    /*
     * sometimes conversion strategy is required to tell the converted how it is expected to read the data.
     * @param strategy -> type: BaseMapper | the strategy must extend BaseMapper, so it can be used by the converter.
     * Currently only CSV requires this feature, all other converters have inbuild conversion strategies. this is cause
     * only csv has linear data, ie it is unstructured and java is not able to do 1-to-1 mapping of the data.
     * */
    public void setConverterStrategy(BaseMapper strategy) throws Exception;

    /*
     * Checks if the provided file is of valid type or not
     * @param path -> path of the file to be validated
     * */
    public boolean isFileValid(String filePath);


    /*
     * this method is used set the type the converter is going to support
     * @param clazz -> class the converted has to support
     * */
    public void supports(Class clazz) throws IllegalAccessException;

}
