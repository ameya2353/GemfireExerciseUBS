package com.ubs.exercise.test.converter;

import com.ubs.exercise.converter.IConverter;
import com.ubs.exercise.converter.json.JsonConverter;
import com.ubs.exercise.converter.json.exception.InvalidJsonFileException;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserJsonMapper;
import com.ubs.exercise.test.TestConstants;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class JsonConverterTest {

    IConverter converter = new JsonConverter();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test()
    public void returnsEmptyListIfPathIsNull() throws Exception {
        List<User> users = converter.convert(null, User.class);
        Assert.assertEquals(users.size(),0);
    }

    @Test()
    public void ifConverterMappingIsDefined() throws Exception{
        exception.expect(IllegalAccessException.class);
        exception.expectMessage(TestConstants.CONVERTER_MAPPING_NOT_REQUIRED_EXCEPTION_MESSEGE);
        converter.setConverterStrategy(new UserJsonMapper());
        converter.convert(TestConstants.CSV_VALID_FILE_PATH, User.class);
    }

    @Test()
    public void ifFileIsInvalid() throws Exception{
        exception.expect(InvalidJsonFileException.class);
        exception.expectMessage(TestConstants.NOT_A_JSON_EXCEPTION_MESSEGE);
        converter.convert(TestConstants.CSV_VALID_FILE_PATH, User.class);
    }

    @Test()
    public void ifEverythingIsFineConvertsTheDataFromJSONToPOJO() throws Exception {
        List<User> users =  converter.convert(TestConstants.JSON_VALID_FILE_PATH, User.class);
        Assert.assertEquals(1,users.size());
    }

    @Test()
    public void checkDataIsBeenConvertedCorrectly() throws Exception {
        List<User> users =  converter.convert(TestConstants.JSON_VALID_FILE_PATH, User.class);
        Assert.assertEquals(TestConstants.TEST_DATA , users.get(0).toString());
    }

}
