package org.gassion.LibrarySpringApp.model;

import lombok.Data;

@Data
public class Person {
    private int id;
    private String name;
    private String phone_number;
    private int yearOfBrith;
}
