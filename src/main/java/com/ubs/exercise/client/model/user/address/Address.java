package com.ubs.exercise.client.model.user.address;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor @Data
public class Address implements Serializable{
    private String street;
    private String city;
    private  String country;

    @Override
    public String toString(){
        return String.format("Street: %s | City: %s | Country: %s" , street , city , country);
    }
}
