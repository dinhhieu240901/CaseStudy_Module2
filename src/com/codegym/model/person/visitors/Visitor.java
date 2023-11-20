package com.codegym.model.person.visitors;

import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.enumerations.GenderPerson;

public class Visitor  {
    private String visitorID;
    private String name;

    private String streetAddress;
    private GenderPerson gender;
    private int age;
    private AgeCategory ageCategory;

    public Visitor(String visitorID, String name, String streetAddress, GenderPerson gender, int age, AgeCategory ageCategory) {
        this.visitorID = visitorID;
        this.name = name;
        this.streetAddress = streetAddress;
        this.gender = gender;
        this.age = age;
        this.ageCategory = ageCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(String visitorID) {
        this.visitorID = visitorID;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public GenderPerson getGender() {
        return gender;
    }

    public void setGender(GenderPerson gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public Visitor(String visitorID, String streetAddress, GenderPerson gender, int age, AgeCategory ageCategory) {
        this.visitorID = visitorID;
        this.streetAddress = streetAddress;
        this.gender = gender;
        this.age = age;
        this.ageCategory = ageCategory;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "visitorID='" + visitorID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + streetAddress + '\'' +
                ", genderPerson=" + gender +
                ", age=" + age +
                ", ageCategory=" + ageCategory +
                '}';
    }
}
