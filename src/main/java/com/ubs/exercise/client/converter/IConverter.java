package com.ubs.exercise.client.converter;


import java.io.File;
import java.io.FileNotFoundException;
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
     * Checks if the provided file is of valid type or not
     * @param path -> path of the file to be validated
     * */
    public boolean isFileValid(String filePath) throws Exception;


    /*
     * this method is used set the type the converter is going to support
     * @param clazz -> class the converted has to support
     * */
    public void supports(Class clazz) throws IllegalAccessException;


    public default boolean isFileExists(String filePath) throws Exception {
        File f = new File(filePath);
        if(!f.exists()){
            throw new FileNotFoundException(String.format("Unable to locate file: %s",filePath));
        }
        return true;
    }


}
