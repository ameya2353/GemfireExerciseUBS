package com.ubs.exercise.client.function.executions;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;
/*
* function execution for user region
*
* */
@OnRegion(region = "user")
public interface UserFunctionExecution {

    /*
     * fetches all data from the server in JSON string format
     *
     * */
    @FunctionId("convertAllToJson")
    public String  getAllUsers();

    /*
     * fetches  data from the server in JSON string format using ID
     *
     * */
    @FunctionId("convertToJson")
    public String  getUser(String id);
}
