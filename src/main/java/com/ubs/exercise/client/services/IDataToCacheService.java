package com.ubs.exercise.client.services;

import java.io.IOException;

public interface IDataToCacheService {

    public boolean persistData(String path) throws Exception;
}
