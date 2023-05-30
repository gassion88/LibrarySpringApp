package org.gassion.LibrarySpringApp.dao;

import org.gassion.LibrarySpringApp.model.Book;
import org.gassion.LibrarySpringApp.model.BorrowedBook;
import org.gassion.LibrarySpringApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO extends DAO<Book>{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getFromID(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, publication_date, author) VALUES (?, ?, ?)", book.getName(), book.getPublicationDate(), book.getAuthor());
    }

    @Override
    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, publication_date=?, author=? WHERE id=?", book.getName(), book.getPublicationDate(), book.getAuthor(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void addBookToBorrowed(int personID , int bookID) {
        jdbcTemplate.update("INSERT INTO borrowed_books(person_id, book_id) VALUES ( ?, ?)", bookID, personID);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM borrowed_books WHERE book_id=?", id);
    }

    public Person getPersonBorrowed(int personID) {
        return jdbcTemplate.query("SELECT * FROM person Join borrowed_books ON person.id=borrowed_books.person_id Where borrowed_books.book_id=?",
                new Object[]{personID}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}
