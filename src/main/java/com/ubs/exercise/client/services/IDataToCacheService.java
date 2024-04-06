package com.ubs.exercise.client.services;

import java.io.IOException;

/*
 *
 * Interface of Data to cache service, any new type of file needs to implement this .
 *
 * */
public interface IDataToCacheService {

    public boolean persistData(String path) throws Exception;
}
