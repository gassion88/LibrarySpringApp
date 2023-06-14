package org.gassion.LibrarySpringApp.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Phone number should not be empty")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "years_of_birth")
    private int yearsOfBirth;

    @OneToMany(mappedBy = "borrowedPerson")
    private List<Book> books;

    public Person(String name, String phoneNumber, int yearsOfBirth) {
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(Book book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }

        this.books.add(book);
        book.setBorrowedPerson(this);
    }
}
