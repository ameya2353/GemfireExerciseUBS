package com.ubs.exercise.test.service;

import com.ubs.exercise.converter.csv.CSVConverter;
import com.ubs.exercise.converter.json.JsonConverter;
import com.ubs.exercise.converter.xml.XMLConverter;
import com.ubs.exercise.fetcher.JsonFetcher;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.CSVToCacheService;
import com.ubs.exercise.services.JsonToCacheService;
import com.ubs.exercise.services.XmlToCacheService;
import com.ubs.exercise.services.user.UserService;
import com.ubs.exercise.test.TestConstants;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.junit.Assert;
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

import java.util.Optional;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    private static final String USER_NAME = "andyng123";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService service;

    @Test
    public void test1_checkIfCSVDataPersists() throws Exception {
        Assert.assertEquals(true , service.persistCSVData(TestConstants.CSV_VALID_FILE_PATH));
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
        userRepo.deleteAll();
        Optional<User> deletedUser = userRepo.findById(USER_NAME);
        Assert.assertEquals(true , deletedUser.isEmpty());
    }

    @Test
    public void test6_checkIfJsonDataPersists() throws Exception {
        Assert.assertEquals(true , service.persistJsonData(TestConstants.JSON_VALID_FILE_PATH));
    }
    @Test
    public void test7_checkIfDataPresentInTheCache(){
        Assert.assertEquals(1 , StreamSupport.stream(userRepo.findAll().spliterator(),false).count());
    }

    @Test
    public void test8_checkIfProperDataPresentInTheCache(){
        Optional<User> user = userRepo.findById(USER_NAME);
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test9_checkDataIntegrityInTheCache(){
        Optional<User> user = userRepo.findById(USER_NAME);
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
        userRepo.deleteAll();
        Optional<User> deletedUser = userRepo.findById(USER_NAME);
        Assert.assertEquals(true , deletedUser.isEmpty());
    }


    @Test
    public void test10_checkIfXmlDataPersists() throws Exception {
        Assert.assertEquals(true , service.persistXmlData(TestConstants.XML_VALID_FILE_PATH));
    }
    @Test
    public void test11_checkIfDataPresentInTheCache(){
        Assert.assertEquals(1 , StreamSupport.stream(userRepo.findAll().spliterator(),false).count());
    }

    @Test
    public void test12_checkIfProperDataPresentInTheCache(){
        Optional<User> user = userRepo.findById(USER_NAME);
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test13_checkDataIntegrityInTheCache(){
        Optional<User> user = userRepo.findById(USER_NAME);
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
        userRepo.deleteAll();
        Optional<User> deletedUser = userRepo.findById(USER_NAME);
        Assert.assertEquals(true , deletedUser.isEmpty());

    }

    @Test
    public void test14_checkIfDataIsFetchedINJsonString() throws Exception {
        service.persistXmlData(TestConstants.XML_VALID_FILE_PATH);
        String jsonString = service.fetchUserDataJson(USER_NAME);
        Assert.assertEquals(TestConstants.JSON_TEST_DATA , jsonString);
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
