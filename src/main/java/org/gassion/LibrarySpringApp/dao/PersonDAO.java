package org.gassion.LibrarySpringApp.dao;

import org.gassion.LibrarySpringApp.model.Book;
import org.gassion.LibrarySpringApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO extends DAO<Person> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person getFromID(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, phone_number, years_of_birth) VALUES (?, ?, ?)", person.getName(), person.getPhoneNumber(), person.getYearsOfBirth());
    }

    @Override
    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, phone_number=?, years_of_birth=? WHERE id=?", person.getName(), person.getPhoneNumber(), person.getYearsOfBirth(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> getAllBorrowedBook(int personID) {
        return jdbcTemplate.query("SELECT * FROM book Join borrowed_books ON book.id=borrowed_books.book_id Where borrowed_books.person_id=?",
                new Object[]{personID}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Person> getFromNumber(String phoneNumber) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE phone_number=?", new Object[]{phoneNumber},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
