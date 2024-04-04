package com.ubs.exercise.converter;

import java.io.IOException;
import java.util.List;

public interface IConverter{
    public <T> List<T> convert(String path , Class<T> type) throws Exception;

}
