package org.gassion.LibrarySpringApp.dao;

import org.gassion.LibrarySpringApp.model.Book;
import org.gassion.LibrarySpringApp.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO extends DAO<Person> {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Person getFromID(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<Person> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> person = session.createQuery("select  p from Person p", Person.class).getResultList();

        return person;
    }

    @Override
    public void add(Person person) {

    }

    @Override
    public void update(int id, Person person) {

    }

    @Override
    public void delete(int id) {


    }

    public List<Book> getAllBorrowedBook(int personID) {
        return null;
    }

    public Optional<Person> getFromNumber(String phoneNumber) {
        return Optional.empty();
    }
}
