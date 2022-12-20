package com.example.railwaystation.Models.UserFiles;

public class UserInfo {
    private String name;
    private String surname;
    private int age;
    private String passportId;
    private String phoneNumber;

    public UserInfo() {
    }

    public UserInfo(String name, String surname, int age, String passportId, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.passportId = passportId;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  name + ' ' + surname  ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
