package com.ubs.exercise.test.persister;

import com.ubs.exercise.converter.csv.CSVConverter;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.address.Address;
import com.ubs.exercise.persister.Persister;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.user.UserService;
import com.ubs.exercise.test.TestConstants;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersisterTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private static final String USER_NAME = "andyng123";

    @Autowired
    private UserRepo userRepo;


    @Test
    public void test1_shouldNotAcceptNullRepository(){
        exception.expect(NullPointerException.class);
        exception.expectMessage(TestConstants.NULL_REPO);
        new Persister<User>(null, Collections.EMPTY_LIST);
    }


    @Test
    public void test2_nullListWillReturnFalseinSave(){
        Persister<User> userPersister = new Persister<>(userRepo, Collections.EMPTY_LIST);
        Assert.assertEquals(false, userPersister.savedata());
    }

    @Test
    public void test3_persisterSaveWillReturnTrueIfEverythingIsFile(){
        List<User> users = List.of(new User("andyng123" , "andy-zc.ng@ubs.com" , new Address("123 UBS Street" , "London" , "UK")));
        Persister<User> userPersister = new Persister<>(userRepo, users);
        Assert.assertEquals(true, userPersister.savedata());
    }

    @Test
    public void test4_checkIfDataPresentInTheCache(){
        Assert.assertEquals(1 , StreamSupport.stream(userRepo.findAll().spliterator(),false).count());
    }

    @Test
    public void test5_checkIfProperDataPresentInTheCache(){
        Optional<User> user = userRepo.findById(USER_NAME);
        Assert.assertEquals(true , user.isPresent());
    }


    @Test
    public void test6_checkDataIntegrityInTheCache(){
        Optional<User> user = userRepo.findById(USER_NAME);
        Assert.assertEquals(TestConstants.TEST_DATA , user.get().toString());
    }


    @ClientCacheApplication
    @EnableEntityDefinedRegions(basePackageClasses = User.class ,   clientRegionShortcut = ClientRegionShortcut.LOCAL)
    @EnableGemfireRepositories(basePackageClasses = UserRepo.class)
    static class TestConfiguration {

        @Bean
        public UserService userCsvService(){
            return new UserService();
        }

        @Bean
        public CSVConverter csvConverter(){
            return new CSVConverter();
        }

    }



}
