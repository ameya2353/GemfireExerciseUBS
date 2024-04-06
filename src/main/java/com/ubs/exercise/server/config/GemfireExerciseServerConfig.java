package com.ubs.exercise.server.config;

import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.server.functions.GemfireExerciseFunction;
import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.GemFireCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.gemfire.PartitionedRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.function.config.EnableGemfireFunctions;

@CacheServerApplication(autoStartup = true, copyOnRead = true, port = 0, locators = "localhost[10334]", logLevel = "error")
@EnableGemfireFunctions
@EnableLocator(host = "localhost", port = 10334)
@Import(GemfireExerciseFunction.class)
public class GemfireExerciseServerConfig {

    @Bean("user")
    protected PartitionedRegionFactoryBean<String, User> customerRegion(GemFireCache gemfireCache) {
        PartitionedRegionFactoryBean<String, User> regionFactoryBean = new PartitionedRegionFactoryBean<>();
        regionFactoryBean.setCache(gemfireCache);
        regionFactoryBean.setRegionName("user");
        regionFactoryBean.setDataPolicy(DataPolicy.PARTITION);
        return regionFactoryBean;
    }
}