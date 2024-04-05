package com.ubs.exercise.model.user;


import com.opencsv.bean.CsvBindByPosition;
import com.ubs.exercise.model.user.address.Address;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

@Data
@Region("user")
@XmlRootElement(name = "user")
public class User implements Serializable {

    @Id
    private String username;

    private String email;

    private Address address;

    @PersistenceConstructor
    public User(String username, String email, Address address) {
        this.username = username;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString(){
        return String.format("UserName: %s | Email: %s | Address: %s" , username , email , address);
    }
}
