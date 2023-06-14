package org.gassion.LibrarySpringApp.dao;

import jakarta.persistence.Query;
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
    @Transactional
    public Person getFromID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Override
    @Transactional
    public List<Person> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select  p from Person p", Person.class).getResultList();
    }

    @Override
    @Transactional
    public void add(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Override
    @Transactional
    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();

        Person updatedPerson = session.get(Person.class, id);
        updatedPerson.setName(person.getName());
        updatedPerson.setPhoneNumber(person.getPhoneNumber());
        updatedPerson.setYearsOfBirth(person.getYearsOfBirth());
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);
        session.remove(person);
    }

    @Transactional
    public List<Book> getAllBorrowedBook(int personID) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, personID);
        System.out.println(person.getBooks());
        return person.getBooks();
    }

    @Transactional
    public Optional<Person> getFromNumber(String phoneNumber) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select b from Person b where b.phoneNumber=:phoneNumber", Person.class);
        query.setParameter("phoneNumber", phoneNumber);

        return query.getResultList().stream().findAny();
    }
}
