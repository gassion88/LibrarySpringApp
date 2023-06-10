package org.gassion.LibrarySpringApp.dao;

import org.gassion.LibrarySpringApp.model.Book;
import org.gassion.LibrarySpringApp.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookDAO extends DAO<Book>{

    public final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book getFromID(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Book> books = session.createQuery("select b from Book b", Book.class).getResultList();

        return books;
    }

    @Override
    public void add(Book book) {

    }

    @Override
    public void update(int id, Book book) {

    }

    @Override
    public void delete(int id) {

    }

    public void addBookToBorrowed(int personID , int bookID) {

    }

    public void deleteBookInBorrowed(int id) {

    }

    public Person getPersonBorrowed(int personID) {
        return null;
    }
}
