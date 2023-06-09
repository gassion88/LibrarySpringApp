package org.gassion.LibrarySpringApp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Book {
    private int id;

    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 2, max = 30, message = "Book name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Publication date should not ve empty")
    private int publicationDate;

    @NotEmpty(message = "Author must be empty")
    private String author;
}
