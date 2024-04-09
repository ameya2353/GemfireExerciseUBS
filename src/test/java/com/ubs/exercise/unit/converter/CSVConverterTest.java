package com.ubs.exercise.unit.converter;

import com.ubs.exercise.client.converter.IConverter;
import com.ubs.exercise.client.converter.IConverterWithStrategy;
import com.ubs.exercise.client.converter.csv.CSVConverter;
import com.ubs.exercise.client.converter.csv.exception.CsvConverterException;
import com.ubs.exercise.client.converter.csv.exception.InvalidCsvFileException;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.UserCsvMapper;
import com.ubs.exercise.TestConstants;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
public class CSVConverterTest {
    private IConverterWithStrategy userCSVConverter = new CSVConverter();

    private final String noFile = "file-not-found.csv";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test()
    public void returnsEmptyListIfPathIsNull() throws Exception {
        List<User> users = userCSVConverter.convert(null);
        Assert.assertEquals(users.size(),0);
    }

    @Test()
    public void supportsFunctionDisabled() throws Exception {
        exception.expect(IllegalAccessException.class);
        exception.expectMessage(TestConstants.SUPPORT_NOT_REQUIRED);
        userCSVConverter.supports(User.class);
    }
    @Test()
    public void ifConverterMappingIsNotDefined() throws Exception{
        exception.expect(CsvConverterException.class);
        exception.expectMessage(TestConstants.CSV_CONVERTER_MAPPING_EXCEPTION_MESSEGE);
        userCSVConverter.convert(TestConstants.CSV_VALID_FILE_PATH);
    }

    @Test()
    public void ifFileIsInvalid() throws Exception {
        exception.expect(InvalidCsvFileException.class);
        exception.expectMessage(TestConstants.NOT_A_CSV_EXCEPTION_MESSEGE);
        userCSVConverter.setConverterStrategy(new UserCsvMapper());
        userCSVConverter.convert(TestConstants.INVALID_FILE_PATH);
    }

    @Test()
    public void thowsFileNotFoundExceptionIfFileDoesntExist() throws Exception {
        exception.expect(FileNotFoundException.class);
        exception.expectMessage(String.format(TestConstants.FILE_NOT_FOUND ,noFile));
        List<User> users = userCSVConverter.convert("file-not-found.csv");
    }

    @Test()
    public void ifEverythingIsFineConvertsTheDataFromCSVToPOJO() throws Exception {
        userCSVConverter.setConverterStrategy(new UserCsvMapper());
       List<User> users =  userCSVConverter.convert(TestConstants.CSV_VALID_FILE_PATH);
       Assert.assertEquals(1,users.size());
    }

    @Test()
    public void checkDataIsBeenConvertedCorrectly() throws Exception {
        userCSVConverter.setConverterStrategy(new UserCsvMapper());
        List<User> users =  userCSVConverter.convert(TestConstants.CSV_VALID_FILE_PATH);
        Assert.assertEquals(TestConstants.TEST_DATA , users.get(0).toString());
    }



}
