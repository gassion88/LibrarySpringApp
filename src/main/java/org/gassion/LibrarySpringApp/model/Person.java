package org.gassion.LibrarySpringApp.model;

public class Person {
    private int id;
    private String name;
    private String phone_number;
    private int yearOfBrith;

    public Person(int id, String name, String phone_number, int yearOfBrith) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.yearOfBrith = yearOfBrith;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getYearOfBrith() {
        return yearOfBrith;
    }

    public void setYearOfBrith(int yearOfBrith) {
        this.yearOfBrith = yearOfBrith;
    }
}
