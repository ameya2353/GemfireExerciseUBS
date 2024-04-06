package com.ubs.exercise.client.configurations;

import com.ubs.exercise.client.converter.BaseMapper;
import com.ubs.exercise.client.fetcher.JsonFetcher;
import com.ubs.exercise.client.services.CSVToCacheService;
import com.ubs.exercise.client.services.JsonToCacheService;
import com.ubs.exercise.client.services.XmlToCacheService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;


/*
* base type to the cache configurations.
* to be extended in the beantocache configuration and specified the generic types.
* I -> Type of the Id
* D -> Domain Type
* R -> Crud repository beloning to the Domain Type
* M -> Mapper for CSV conversion.
* */
public class ToCachConfiguration <I , D , R extends CrudRepository, M extends BaseMapper>{

    protected Class clazz;
    @Bean
    public CSVToCacheService<D, R, M> CSVToCacheService(){
        return new CSVToCacheService<D, R, M>();
    }

    @Bean
    public JsonToCacheService<D, R> jsonToCacheService(){
        return new JsonToCacheService<D, R>(clazz);
    }

    @Bean
    public XmlToCacheService<D, R> xmlToCacheService(){
        return new XmlToCacheService<D, R>(clazz);
    }

    @Bean
    public JsonFetcher<I, R> jsonFetcher(){
        return new JsonFetcher<I, R>();
    }

}
