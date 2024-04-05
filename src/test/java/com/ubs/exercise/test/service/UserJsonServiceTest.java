package com.ubs.exercise.test.service;

import com.ubs.exercise.converter.csv.CSVConverter;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.IDataToCacheService;
import com.ubs.exercise.services.user.UserJsonService;
import com.ubs.exercise.test.TestConstants;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class UserJsonServiceTest {
    private static final String USER_NAME = "andyng123";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private IDataToCacheService service;

    @Test
    public void test1_checkIfDataPersists() throws Exception {
        Assert.assertEquals(true , service.persistData(TestConstants.CSV_VALID_FILE_PATH));
    }
    @Test
    public void test2_checkIfDataPresentInTheCache(){
        Assert.assertEquals(1 , StreamSupport.stream(userRepo.findAll().spliterator(),false).count());
    }

    @Test
    public void test3_checkIfProperDataPresentInTheCache(){
        Optional<User> user = userRepo.findById(USER_NAME);
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test4_checkDataIntegrityInTheCache(){
        Optional<User> user = userRepo.findById(USER_NAME);
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
    }




    @ClientCacheApplication
    @EnableEntityDefinedRegions(basePackageClasses = User.class ,   clientRegionShortcut = ClientRegionShortcut.LOCAL)
    @EnableGemfireRepositories(basePackageClasses = UserRepo.class)
    static class TestConfiguration {

        @Bean
        public UserJsonService userCsvService(){
            return new UserJsonService();
        }

        @Bean
        public CSVConverter csvConverter(){
            return new CSVConverter();
        }

    }
}
