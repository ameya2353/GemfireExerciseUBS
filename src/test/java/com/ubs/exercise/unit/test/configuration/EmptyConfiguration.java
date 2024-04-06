package com.ubs.exercise.unit.test.configuration;

import com.ubs.exercise.client.fetcher.JsonFetcher;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.repository.UserRepo;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@ClientCacheApplication
@EnableEntityDefinedRegions(basePackageClasses = User.class, clientRegionShortcut = ClientRegionShortcut.LOCAL )
@EnableGemfireRepositories(basePackageClasses = UserRepo.class)
public class EmptyConfiguration {
    @Bean
    public JsonFetcher<String, UserRepo> jsonFetcher() {
        return new JsonFetcher<String, UserRepo>();
    }
}
