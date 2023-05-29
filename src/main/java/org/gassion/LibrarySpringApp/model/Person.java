package org.gassion.LibrarySpringApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Person {
    private int id;
    private String name;
    private String phone_number;
    private int year_of_brith;
}
