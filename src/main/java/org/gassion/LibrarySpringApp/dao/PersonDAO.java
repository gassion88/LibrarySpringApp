package org.gassion.LibrarySpringApp.dao;

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
    public Optional<Person> getFromID(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, phone_number, years_of_birth) VALUES (?, ?, ?)", person.getName(), person.getPhone_number(), person.getYearOfBrith());
    }

    @Override
    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, phone_number=?, year_of_birth=? WHERE id=?", person.getName(), person.getPhone_number(), person.getYearOfBrith(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
