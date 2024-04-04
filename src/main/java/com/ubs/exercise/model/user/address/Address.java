package com.ubs.exercise.model.user.address;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private  String country;

    @Override
    public String toString(){
        return String.format("Street: %s | City: %s | Country: %s" , street , city , country);
    }
}
