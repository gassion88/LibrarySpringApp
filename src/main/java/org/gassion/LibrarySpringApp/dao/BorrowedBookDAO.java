package org.gassion.LibrarySpringApp.dao;

import org.gassion.LibrarySpringApp.model.Book;
import org.gassion.LibrarySpringApp.model.BorrowedBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BorrowedBookDAO {
    List<Book> getAllBorrowedBookPerson();


}
