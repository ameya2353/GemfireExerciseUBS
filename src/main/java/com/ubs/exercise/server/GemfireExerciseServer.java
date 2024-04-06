package com.ubs.exercise.server;

import com.ubs.exercise.server.config.GemfireExerciseServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.gemfire.config.annotation.EnableManager;

@SpringBootApplication
@ComponentScan(basePackages = "com.ubs.exercise.server.functions")
@Import(GemfireExerciseServerConfig.class)
public class GemfireExerciseServer {
    public static void main(String[] args) {
        SpringApplication.run(GemfireExerciseServer.class, args);
    }

}