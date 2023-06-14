package org.gassion.LibrarySpringApp.service;

import org.gassion.LibrarySpringApp.model.Book;
import org.gassion.LibrarySpringApp.model.Person;
import org.gassion.LibrarySpringApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int bookID) {
        Optional<Book> book = bookRepository.findById(bookID);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int bookID, Book book) {
        book.setId(bookID);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int bookID) {
        bookRepository.deleteById(bookID);
    }
}
