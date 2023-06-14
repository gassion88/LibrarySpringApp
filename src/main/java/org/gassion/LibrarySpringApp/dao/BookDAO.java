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
    @Transactional
    public Book getFromID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    @Transactional
    public void add(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Override
    @Transactional
    public void update(int id, Book book) {
        Session session = sessionFactory.getCurrentSession();

        Book updatedBook = session.get(Book.class, id);
        updatedBook.setName(book.getName());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setPublicationDate(book.getPublicationDate());
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);
        session.remove(person);
    }

    @Transactional
    public void addBookToBorrowed(int personID , int bookID) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, bookID);
        Person person = session.get(Person.class, personID);
        book.setBorrowedPerson(person);
    }

    @Transactional
    public void deleteBookInBorrowed(int bookID) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, bookID);
        book.setBorrowedPerson(null);
    }

    @Transactional
    public Person getPersonIfBorrowed(int bookID) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, bookID);
        return book.getBorrowedPerson();
    }
}
