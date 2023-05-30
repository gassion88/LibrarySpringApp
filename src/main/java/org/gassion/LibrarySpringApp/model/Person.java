package org.gassion.LibrarySpringApp.model;

public class Person {
    private int id;
    private String name;
    private String phoneNumber;
    private int yearsOfBirth;

    public Person(int id, String name, String phoneNumber, int yearsOfBirth) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.yearsOfBirth = yearsOfBirth;
    }

    public Person() {;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getYearsOfBirth() {
        return yearsOfBirth;
    }

    public void setYearsOfBirth(int yearsOfBirth) {
        this.yearsOfBirth = yearsOfBirth;
    }
}
