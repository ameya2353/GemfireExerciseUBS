package com.ubs.exercise.client.configurations;

import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.repository.UserRepo;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.function.config.EnableGemfireFunctionExecutions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.transaction.config.EnableGemfireCacheTransactions;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
* configuration class for gemfire client
* contains configuration for user region in client server
* */

@ClientCacheApplication(name = "GemFireExerciseClientCache", logLevel = "error", pingInterval = 5000L, readTimeout = 15000, retryAttempts = 1)
@EnableGemfireFunctionExecutions(basePackages = {"com.ubs.exercise.client.function.executions"})
@EnableGemfireRepositories(basePackageClasses = UserRepo.class)
@ComponentScan(basePackages = {"com.ubs.exercise.client"})
@EnableGemfireCacheTransactions
@EnableTransactionManagement
public class GemfireClientConfiguration {
    @Bean("user")
    protected ClientRegionFactoryBean<Long, User> userRegion(GemFireCache gemfireCache) {
        ClientRegionFactoryBean<Long, User> regionFactoryBean = new ClientRegionFactoryBean<>();
        regionFactoryBean.setCache(gemfireCache);
        regionFactoryBean.setRegionName("user");
        regionFactoryBean.setShortcut(ClientRegionShortcut.PROXY);
        return regionFactoryBean;
    }

}