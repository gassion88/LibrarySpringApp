package org.gassion.LibrarySpringApp.repository;

import org.gassion.LibrarySpringApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
