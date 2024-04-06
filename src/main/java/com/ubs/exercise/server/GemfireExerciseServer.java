package com.ubs.exercise.server;

import com.ubs.exercise.server.config.GemfireExerciseServerConfig;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.gemfire.config.annotation.EnableManager;

import java.util.Scanner;

@SpringBootApplication
@EnableManager(start = true)
@ComponentScan(basePackages = "com.ubs.exercise.server.functions")
@Import(GemfireExerciseServerConfig.class)
public class GemfireExerciseServer {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(GemfireExerciseServer.class)
                .web(WebApplicationType.NONE)
                .build();

        String profile = "default";
        if(args.length != 0) {
            profile = args[0];
        }

        springApplication.setAdditionalProfiles(profile);
        springApplication.run(args);
    }

    @Bean
    ApplicationRunner runner() {
        return args -> new Scanner(System.in).nextLine();
    }
}