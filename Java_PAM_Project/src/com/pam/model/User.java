package com.pam.model;

public class User {
    String gender;
    String name;
    String email;
    int age;

    String getGender() {
        return gender;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    String getAge() {
        return age + "";
    }
}
