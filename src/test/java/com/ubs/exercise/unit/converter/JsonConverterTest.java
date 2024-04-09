package com.ubs.exercise.unit.converter;

import com.ubs.exercise.client.converter.IConverter;
import com.ubs.exercise.client.converter.json.JsonConverter;
import com.ubs.exercise.client.converter.json.exception.InvalidJsonFileException;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.UserCsvMapper;
import com.ubs.exercise.TestConstants;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsonConverterTest {

    private final String noFile = "file-not-found.json";
    IConverter converter = new JsonConverter();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setClass() throws IllegalAccessException {
        converter.supports(User.class);
    }

    @Test()
    public void test1_returnsEmptyListIfPathIsNull() throws Exception {
        converter.supports(User.class);
        List<User> users = converter.convert(null);
        Assert.assertEquals(users.size(),0);
    }




    @Test()
    public void test3_ifFileIsInvalid() throws Exception{
        exception.expect(InvalidJsonFileException.class);
        exception.expectMessage(TestConstants.NOT_A_JSON_EXCEPTION_MESSEGE);
        converter.convert(TestConstants.CSV_VALID_FILE_PATH);
    }

    @Test()
    public void thowsFileNotFoundExceptionIfFileDoesntExist() throws Exception {
        exception.expect(FileNotFoundException.class);
        exception.expectMessage(String.format(TestConstants.FILE_NOT_FOUND ,noFile));
        List<User> users = converter.convert(noFile);
    }

    @Test()
    public void test3_ifEverythingIsFineConvertsTheDataFromJSONToPOJO() throws Exception {
        List<User> users =  converter.convert(TestConstants.JSON_VALID_FILE_PATH);
        Assert.assertEquals(1,users.size());
    }

    @Test()
    public void test4_checkDataIsBeenConvertedCorrectly() throws Exception {
        List<User> users =  converter.convert(TestConstants.JSON_VALID_FILE_PATH);
        Assert.assertEquals(TestConstants.TEST_DATA , users.get(0).toString());
    }

}
