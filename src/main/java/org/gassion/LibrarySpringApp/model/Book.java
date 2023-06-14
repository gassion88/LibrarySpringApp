package org.gassion.LibrarySpringApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrowed_person", referencedColumnName = "id")
    private Person borrowedPerson;

    public Book() {
    }

    public Book(String name, int publicationDate, String author, Person borrowedPerson) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.author = author;
        this.borrowedPerson = borrowedPerson;
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

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person getBorrowedPerson() {
        return borrowedPerson;
    }

    public void setBorrowedPerson(Person borrowedPerson) {
        this.borrowedPerson = borrowedPerson;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicationDate=" + publicationDate +
                ", author='" + author + '\'' +
                ", borrowedPerson=" + borrowedPerson +
                '}';
    }
}
