package com.ubs.exercise.test.converter;

import com.ubs.exercise.converter.IConverter;
import com.ubs.exercise.converter.json.JsonConverter;
import com.ubs.exercise.converter.json.exception.InvalidJsonFileException;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.test.TestConstants;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsonConverterTest {

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
    public void test2_ifConverterMappingIsDefined() throws Exception{
        exception.expect(IllegalAccessException.class);
        exception.expectMessage(TestConstants.CONVERTER_MAPPING_NOT_REQUIRED_EXCEPTION_MESSEGE);
        converter.setConverterStrategy(new UserCsvMapper());
        converter.convert(TestConstants.CSV_VALID_FILE_PATH);
    }



    @Test()
    public void test3_ifFileIsInvalid() throws Exception{
        exception.expect(InvalidJsonFileException.class);
        exception.expectMessage(TestConstants.NOT_A_JSON_EXCEPTION_MESSEGE);
        converter.convert(TestConstants.CSV_VALID_FILE_PATH);
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
