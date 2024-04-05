package com.ubs.exercise.test.fetcher;

import com.ubs.exercise.converter.csv.CSVConverter;
import com.ubs.exercise.converter.json.JsonConverter;
import com.ubs.exercise.converter.xml.XMLConverter;
import com.ubs.exercise.fetcher.JsonFetcher;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.model.user.address.Address;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.CSVToCacheService;
import com.ubs.exercise.services.JsonToCacheService;
import com.ubs.exercise.services.XmlToCacheService;
import com.ubs.exercise.services.user.UserService;
import com.ubs.exercise.test.TestConstants;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsonFetcherTest {

    private static final String USER_NAME = "andyng123";
    @Autowired
    JsonFetcher<String , UserRepo> fetcher;

    @Autowired
    UserRepo repo;

    @Before
    public void beforeTest(){
        Address address = new Address("123 UBS Street" , "London" , "UK");
        User user = new User("andyng123" , "andy-zc.ng@ubs.com" , address);
        User user2 = new User("andyng1234" , "andy-zc.ng@ubs.com" , address);
        repo.save(user);
        repo.save(user2);
    }

    @Test
    public void test1_checkIfSingleDataIsFetched() throws Exception {

        String jsonString = fetcher.fetch(USER_NAME);
        Assert.assertEquals(TestConstants.JSON_TEST_DATA , jsonString);
    }

    @Test
    public void test1_checkIfMultipleDataIsFetched() throws Exception {
        String jsonString = fetcher.fetchAll(List.of("andyng123","andyng1234"));
        System.out.println(jsonString);
    }




    @ClientCacheApplication
    @EnableEntityDefinedRegions(basePackageClasses = User.class ,   clientRegionShortcut = ClientRegionShortcut.LOCAL)
    @EnableGemfireRepositories(basePackageClasses = UserRepo.class)
    static class TestConfiguration {

        @Bean
        public UserService userService(){
            return new UserService();
        }

        @Bean
        public CSVConverter csvConverter(){
            return new CSVConverter();
        }

        @Bean
        public UserCsvMapper csvMapper(){
            return new UserCsvMapper();
        }

        @Bean
        public CSVToCacheService CSVToCacheService(){
            return new CSVToCacheService<User , UserRepo ,UserCsvMapper>();
        }

        @Bean
        public JsonConverter jsonConverter(){
            return new JsonConverter();
        }

        @Bean
        public JsonToCacheService JsonToCacheService(){
            JsonToCacheService service = new JsonToCacheService<User, UserRepo>(User.class);
            return service;
        }

        @Bean
        public XmlToCacheService xmlToCacheService(){
            XmlToCacheService service = new XmlToCacheService<User, UserRepo>(User.class);
            return service;
        }


        @Bean
        public XMLConverter xmlConverter(){
            return new XMLConverter();
        }

        @Bean
        public JsonFetcher<String, UserRepo> jsonFetcher(){
            return new JsonFetcher<String, UserRepo>();
        }

    }

}
