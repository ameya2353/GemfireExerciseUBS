package com.ubs.exercise.client.converter.xml;

import com.ubs.exercise.client.converter.BaseMapper;
import com.ubs.exercise.client.converter.IConverter;
import com.ubs.exercise.client.converter.xml.exception.InvalidXMLFileException;
import com.ubs.exercise.client.model.XmlWrapper;
import com.ubs.exercise.client.model.user.User;
import com.ubs.exercise.client.model.user.address.Address;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component("xmlConverter")
public class XMLConverter<T> implements IConverter {
    private Class<T> type;

    @Override
    public <T> List<T> convert(String path) throws Exception {
        if(path==null){
            log.info("No path specified.");
            return Collections.EMPTY_LIST;
        }
        isFileValid(path);
        return (List<T>) unmarshalXMLToList(path , type);
    }

    @Override
    public void setConverterStrategy(BaseMapper strategy) throws Exception {
        throw new IllegalAccessException("XML Converter does not require Converter Strategy. Please remove the setConverterStrategy function call");
    }

    @Override
    public boolean isFileValid(String filePath) {
        if(filePath.endsWith(".xml")){
            return true;
        }
        throw new InvalidXMLFileException("Provided file is not a xml file");
    }

    @Override
    public void supports(Class clazz) throws IllegalAccessException {
        this.type = clazz;
    }

    private <T> List<T> unmarshalXMLToList(String filePath, Class<T> type) throws JAXBException {
            File xmlFile = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(type, XmlWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlWrapper<T> wrapper = (XmlWrapper<T>) jaxbUnmarshaller.unmarshal(xmlFile);
            return wrapper.getItems();
    }


}
