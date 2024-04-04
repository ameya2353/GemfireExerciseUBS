package com.ubs.exercise.test;

import com.ubs.exercise.converter.csv.CSVConverter;
import com.ubs.exercise.converter.exception.CsvConverterException;
import com.ubs.exercise.converter.exception.InvalidCsvFileException;
import com.ubs.exercise.model.user.User;
import com.ubs.exercise.model.user.UserCsvMapper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
public class CSVConverterTest {
    private  CSVConverter userCSVConverter = new CSVConverter();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test()
    public void returnsEmptyListIfPathIsNull() throws IOException {
        List<User> users = userCSVConverter.convert(null, User.class);
        Assert.assertEquals(users.size(),0);
    }

    @Test()
    public void ifConverterMappingIsNotDefined() throws IOException {
        exception.expect(CsvConverterException.class);
        exception.expectMessage("Converter Mapping not provided , set the converter mapping");
        userCSVConverter.convert("test-space/csv/valid-csv-file/testfile.csv" , User.class);
    }

    @Test()
    public void ifFileIsInvalid() throws IOException {
        exception.expect(InvalidCsvFileException.class);
        exception.expectMessage("Provided file is not a csv file");
        userCSVConverter.convert("test-space/csv/invalid-file-path/testfile" , User.class);
    }

    @Test()
    public void ifEverythingIsFineConvertsTheDataFromCSVToPOJO() throws IOException {
        userCSVConverter.setConverter(new UserCsvMapper());
       List<User> users =  userCSVConverter.convert("test-space/csv/valid-csv-file/testFile.csv" , User.class);
       Assert.assertEquals(users.size(),1);
    }

    @Test()
    public void checkDataIsBeenConvertedCorrectly() throws IOException {
        userCSVConverter.setConverter(new UserCsvMapper());
        List<User> users =  userCSVConverter.convert("test-space/csv/valid-csv-file/testFile.csv" , User.class);

        System.out.println(users.get(0));
        Assert.assertEquals("UserName: andyng123 | Email: andy-zc.ng@ubs.com | Address: Street: 123 UBS Street | City: London | Country: UK" , users.get(0).toString());
    }



}
