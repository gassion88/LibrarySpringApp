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
public class BookDAO extends DAO<Book>{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Book> getFromID(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, publication_date, author) VALUES (?, ?, ?)", book.getName(), book.getPublication_year(), book.getAuthor());
    }

    @Override
    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, publication_date=?, author=? WHERE id=?", book.getName(), book.getPublication_year(), book.getAuthor(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
