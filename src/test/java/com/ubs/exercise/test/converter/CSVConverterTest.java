package com.ubs.exercise.test.converter;

import com.ubs.exercise.converter.IConverter;
import com.ubs.exercise.converter.csv.CSVConverter;
import com.ubs.exercise.converter.csv.exception.CsvConverterException;
import com.ubs.exercise.converter.csv.exception.InvalidCsvFileException;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import com.ubs.exercise.test.TestConstants;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class CSVConverterTest {
    private IConverter userCSVConverter = new CSVConverter();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test()
    public void returnsEmptyListIfPathIsNull() throws Exception {
        List<User> users = userCSVConverter.convert(null, User.class);
        Assert.assertEquals(users.size(),0);
    }

    @Test()
    public void ifConverterMappingIsNotDefined() throws Exception{
        exception.expect(CsvConverterException.class);
        exception.expectMessage(TestConstants.CSV_CONVERTER_MAPPING_EXCEPTION_MESSEGE);
        userCSVConverter.convert(TestConstants.CSV_VALID_FILE_PATH, User.class);
    }

    @Test()
    public void ifFileIsInvalid() throws Exception {
        exception.expect(InvalidCsvFileException.class);
        exception.expectMessage(TestConstants.NOT_A_CSV_EXCEPTION_MESSEGE);
        userCSVConverter.setConverterStrategy(new UserCsvMapper());
        userCSVConverter.convert(TestConstants.INVALID_FILE_PATH, User.class);
    }

    @Test()
    public void ifEverythingIsFineConvertsTheDataFromCSVToPOJO() throws Exception {
        userCSVConverter.setConverterStrategy(new UserCsvMapper());
       List<User> users =  userCSVConverter.convert(TestConstants.CSV_VALID_FILE_PATH, User.class);
       Assert.assertEquals(1,users.size());
    }

    @Test()
    public void checkDataIsBeenConvertedCorrectly() throws Exception {
        userCSVConverter.setConverterStrategy(new UserCsvMapper());
        List<User> users =  userCSVConverter.convert(TestConstants.CSV_VALID_FILE_PATH, User.class);
        Assert.assertEquals(TestConstants.TEST_DATA , users.get(0).toString());
    }



}
