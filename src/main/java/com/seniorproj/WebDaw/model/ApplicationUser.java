package com.seniorproj.WebDaw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.GeneratedValue;

@Document(collection = "user")
public class ApplicationUser {

    @Id
//    @GeneratedValue
    private String id;
    private String name;
    private String pass;

    public ApplicationUser() {
    }

    public ApplicationUser(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", pass='" + pass + '\'' +
//                '}';
//    }
}
