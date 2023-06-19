package org.gassion.LibrarySpringApp.repository;

import org.gassion.LibrarySpringApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
