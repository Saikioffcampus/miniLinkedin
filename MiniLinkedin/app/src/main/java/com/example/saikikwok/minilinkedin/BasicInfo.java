package com.example.saikikwok.minilinkedin;

/**
 * Created by saikikwok on 8/24/16.
 */

public class BasicInfo {

    private String name;
    private String email;

    public BasicInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BasicInfo{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
