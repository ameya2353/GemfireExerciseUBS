package com.ubs.exercise.unit.service;

import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.repository.UserRepo;
import com.ubs.exercise.client.services.user.UserService;
import com.ubs.exercise.TestConstants;
import com.ubs.exercise.unit.test.configuration.TestConfiguration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import(TestConfiguration.class)
public class UserServiceTest {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService service;

    @Test
    public void test1_checkIfCSVDataPersists() throws Exception {
        File f = new File("test");
        System.out.println("file-path :"+f.getAbsolutePath());

        Assert.assertEquals(true , service.persistCSVData(TestConstants.CSV_VALID_FILE_PATH));
    }
    @Test
    public void test2_checkIfDataPresentInTheCache(){
        Assert.assertEquals(1 , StreamSupport.stream(userRepo.findAll().spliterator(),false).count());
    }

    @Test
    public void test3_checkIfProperDataPresentInTheCache(){
        Optional<User> user = userRepo.findById(TestConstants.USER_NAME);
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test4_checkDataIntegrityInTheCache(){
        Optional<User> user = userRepo.findById(TestConstants.USER_NAME);
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
        userRepo.deleteAll();
        Optional<User> deletedUser = userRepo.findById(TestConstants.USER_NAME);
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
        Optional<User> user = userRepo.findById(TestConstants.USER_NAME);
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test9_checkDataIntegrityInTheCache(){
        Optional<User> user = userRepo.findById(TestConstants.USER_NAME);
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
        userRepo.deleteAll();
        Optional<User> deletedUser = userRepo.findById(TestConstants.USER_NAME);
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
        Optional<User> user = userRepo.findById(TestConstants.USER_NAME);
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test13_checkDataIntegrityInTheCache(){
        Optional<User> user = userRepo.findById(TestConstants.USER_NAME);
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
        userRepo.deleteAll();
        Optional<User> deletedUser = userRepo.findById(TestConstants.USER_NAME);
        Assert.assertEquals(true , deletedUser.isEmpty());

    }

}
