package com.ubs.exercise.unit.converter;

import com.ubs.exercise.client.converter.IConverter;
import com.ubs.exercise.client.converter.xml.XMLConverter;
import com.ubs.exercise.client.converter.xml.exception.InvalidXMLFileException;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.UserCsvMapper;
import com.ubs.exercise.TestConstants;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XmlConverterTest {
    IConverter converter = new XMLConverter<User>();
    @Before
    public void setClass() throws IllegalAccessException {
        converter.supports(User.class);
    }
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test()
    public void test1_returnsEmptyListIfPathIsNull() throws Exception {
        List<User> users = converter.convert(null);
        Assert.assertEquals(users.size(),0);
    }

    @Test()
    public void test2_ifConverterMappingIsDefined() throws Exception{
        exception.expect(IllegalAccessException.class);
        exception.expectMessage(TestConstants.XML_CONVERTER_MAPPING_NOT_REQUIRED_EXCEPTION_MESSEGE);
        converter.setConverterStrategy(new UserCsvMapper());
        converter.convert(TestConstants.CSV_VALID_FILE_PATH);
    }

    @Test()
    public void test3_ifFileIsInvalid() throws Exception{
        exception.expect(InvalidXMLFileException.class);
        exception.expectMessage(TestConstants.NOT_A_XML_EXCEPTION_MESSEGE);
        converter.convert(TestConstants.CSV_VALID_FILE_PATH);
    }

    @Test()
    public void test3_ifEverythingIsFineConvertsTheDataFromJSONToPOJO() throws Exception {
        List<User> users =  converter.convert(TestConstants.XML_VALID_FILE_PATH);
        Assert.assertEquals(1,users.size());
    }

    @Test()
    public void test4_checkDataIsBeenConvertedCorrectly() throws Exception {
        List<User> users =  converter.convert(TestConstants.XML_VALID_FILE_PATH);
        Assert.assertEquals(TestConstants.TEST_DATA , users.get(0).toString());
    }
}
