package com.seniorproj.WebDaw.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class ApplicationUser {

    @Id
    private ObjectId id;
    private String username;
    private String pass;

    public ApplicationUser() { }

    public ApplicationUser(ObjectId id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
