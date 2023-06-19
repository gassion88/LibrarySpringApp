package org.gassion.LibrarySpringApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

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

    @Column(name = "borrowed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowedAt;

    @Transient
    private boolean isExpired;

    public Book() {
    }

    public Book(String name, int publicationDate, String author, Person borrowedPerson, Date borrowedAt) {
        this.name = name;
        this.publicationDate = publicationDate;
        this.author = author;
        this.borrowedPerson = borrowedPerson;
        this.borrowedAt = borrowedAt;
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

    public Date getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(Date borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public boolean isExpired() {
        if (this.borrowedAt == null) {
            return false;
        }

        return (new Date().getTime() - borrowedAt.getTime()) / 86400000 > 10;
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
