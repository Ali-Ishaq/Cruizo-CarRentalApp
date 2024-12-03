/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.models;

/**
 *
 * @author ALI
 */
public class User {
    String FirstName;
    String LastName;
    Integer Age;

    public User(String FirstName, String LastName, Integer Age) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Integer getAge() {
        return Age;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void LastName(String LastName) {
        this.LastName = LastName;
    }

    public void setAge(Integer Age) {
        this.Age = Age;
    }
    
    
}
