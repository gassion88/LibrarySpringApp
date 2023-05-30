package org.gassion.LibrarySpringApp.model;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String name;
    private int publicationDate;
    private String author;
}
