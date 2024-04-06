package com.ubs.exercise.client.converter.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ubs.exercise.client.converter.BaseMapper;

import java.io.IOException;

/*
 *
 * Mapper for JSON objects we do not need this at the moment but kept it, if needed in future.
 *
 * */

public class Mapper<T> extends JsonDeserializer<T> implements BaseMapper {
    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        throw new IllegalAccessError("Implement mapper for you own entity");
    }
}
