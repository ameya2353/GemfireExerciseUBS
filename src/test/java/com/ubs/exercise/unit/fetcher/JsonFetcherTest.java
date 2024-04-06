package com.ubs.exercise.unit.fetcher;

import com.ubs.exercise.client.fetcher.JsonFetcher;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.address.Address;
import com.ubs.exercise.client.repository.UserRepo;
import com.ubs.exercise.TestConstants;
import com.ubs.exercise.unit.test.configuration.TestConfiguration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import(TestConfiguration.class)
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

    @After
    public void afterTest(){
        repo.deleteAll();
    }

    @Test
    public void test1_checkIfSingleDataIsFetched() throws Exception {
        String jsonString = fetcher.fetch(USER_NAME);
        Assert.assertEquals(TestConstants.JSON_TEST_DATA , jsonString);
    }

    @Test
    public void test1_checkIfMultipleDataIsFetched() throws Exception {
        String jsonString = fetcher.fetchAll(List.of("andyng123","andyng1234"));
        Assert.assertEquals(TestConstants.MULTIPLE_JSON_TEST_DATA , jsonString);
    }

}
