package com.ubs.exercise.client.fetcher;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface Ifetcher<T , I> {

    public T fetch(I id) throws Exception;

    public T fetchAll(List<I> ids) throws Exception;


}
