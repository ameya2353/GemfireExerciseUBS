package com.ubs.exercise.client.model.user;


import com.ubs.exercise.client.model.user.address.Address;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

@Data
@Region(name = "user")
@XmlRootElement(name = "user")
@NoArgsConstructor
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
