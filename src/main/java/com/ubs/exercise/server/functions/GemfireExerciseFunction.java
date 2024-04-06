package com.ubs.exercise.server.functions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubs.exercise.client.model.user.User;
import org.apache.geode.cache.Region;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;

import javax.annotation.Resource;
import java.util.Collection;

public class GemfireExerciseFunction {

    @Resource(name = "user")
    private Region<String, User> users;

    @GemfireFunction(id = "convertAllToJson", HA = true, optimizeForWrite = false, batchSize = 0, hasResult = true)
    public String  getAllUsers() throws JsonProcessingException {
        Collection<User> userCollection =  users.values();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(userCollection);
    }

    @GemfireFunction(id = "convertToJson", HA = true, optimizeForWrite = false, batchSize = 0, hasResult = true)
    public String  getUser(String id) throws JsonProcessingException {
        User user =  users.get(id);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }
}
