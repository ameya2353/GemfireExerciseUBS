package com.ubs.exercise.services;

import java.io.IOException;

public interface IDataToCacheService {

    public boolean persistData(String path) throws Exception;
}
