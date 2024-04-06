package com.ubs.exercise.client.function.executions;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;

@OnRegion(region = "user")
public interface UserFunctionExecution {

    @FunctionId("convertAllToJson")
    public String  getAllUsers();


    @FunctionId("convertToJson")
    public String  getUser(String id);
}
