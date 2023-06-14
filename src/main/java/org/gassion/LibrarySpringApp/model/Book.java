package org.gassion.LibrarySpringApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 2, max = 30, message = "Book name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Publication date should not ve empty")
    @Column(name = "publication_date")
    private int publicationDate;

    @NotEmpty(message = "Author must be empty")
    @Column(name = "author")
    private String author;

    @OneToOne
    @JoinColumn(name = "borrowed_person", referencedColumnName = "id")
    private Person borrowedPerson;
}
