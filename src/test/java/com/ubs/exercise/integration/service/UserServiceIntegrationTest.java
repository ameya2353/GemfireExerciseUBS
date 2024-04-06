package com.ubs.exercise.integration.service;

import com.ubs.exercise.TestConstants;
import com.ubs.exercise.client.configurations.GemfireClientConfiguration;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.repository.UserRepo;
import com.ubs.exercise.client.services.user.UserService;
import com.ubs.exercise.server.GemfireExerciseServer;
import org.apache.geode.cache.Region;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.gemfire.tests.integration.ForkingClientServerIntegrationTestsSupport;
import org.springframework.data.gemfire.util.RegionUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = GemfireClientConfiguration.class)
public class UserServiceIntegrationTest extends ForkingClientServerIntegrationTestsSupport {
    private static final String USER_NAME = "andyng123";
    @Resource(name = "user")
    private Region<Long, User> userRegion;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo repo;


    @BeforeClass
    public static void setup() throws IOException {
        startGemFireServer(GemfireExerciseServer.class);
    }

    @Test
    public void test1_customersRegionWasConfiguredCorrectly() {
        assertThat(this.userRegion).isNotNull();
        assertThat(this.userRegion.getName()).isEqualTo("user");
        assertThat(this.userRegion.getFullPath()).isEqualTo(RegionUtils.toRegionPath("user"));
    }

    @Test
    public void test2_userServiceWasConfiguredCorrectly() {
        assertThat(this.userService).isNotNull();
    }


    @Test
    public void test3_checkIfCSVDataPersists() throws Exception {
        Assert.assertEquals(true , userService.persistCSVData(TestConstants.CSV_VALID_FILE_PATH));
    }
      @Test
    public void test4_checkIfProperDataPresentInTheCache(){
        Optional<User> user = Optional.of(userRegion.get(USER_NAME));
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test5_checkDataIntegrityInTheCache(){
        Optional<User> user = Optional.of(userRegion.get(USER_NAME));
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
        repo.deleteAll();
        Optional<User> deletedUser =  Optional.ofNullable(userRegion.get(USER_NAME));
        Assert.assertEquals(true , deletedUser.isEmpty());
    }

    @Test
    public void test6_checkIfJsonDataPersists() throws Exception {
        Assert.assertEquals(true , userService.persistJsonData(TestConstants.JSON_VALID_FILE_PATH));
    }

    @Test
    public void test7_checkIfProperDataPresentInTheCache(){
        Optional<User> user = Optional.of(userRegion.get(USER_NAME));
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test9_checkDataIntegrityInTheCache(){
        Optional<User> user = Optional.of(userRegion.get(USER_NAME));
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
        repo.deleteAll();
        Optional<User> deletedUser =  Optional.ofNullable(userRegion.get(USER_NAME));
        Assert.assertEquals(true , deletedUser.isEmpty());
    }


    @Test
    public void test10_checkIfXmlDataPersists() throws Exception {
        Assert.assertEquals(true , userService.persistXmlData(TestConstants.XML_VALID_FILE_PATH));
    }


    @Test
    public void test11_checkIfProperDataPresentInTheCache(){
        Optional<User> user = Optional.of(userRegion.get(USER_NAME));
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test13_checkDataIntegrityInTheCache(){
        Optional<User> user = Optional.of(userRegion.get(USER_NAME));
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
        repo.deleteAll();
        Optional<User> deletedUser =  Optional.ofNullable(userRegion.get(USER_NAME));
        Assert.assertEquals(true , deletedUser.isEmpty());

    }

    @Test
    public void test14_checkIfDataIsFetchedINJsonString() throws Exception {
        userService.persistXmlData(TestConstants.XML_VALID_FILE_PATH);
        String jsonString = userService.fetchUserDataJson(USER_NAME);
        Assert.assertEquals(TestConstants.JSON_TEST_DATA , jsonString);
    }
}
