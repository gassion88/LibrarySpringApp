package org.gassion.LibrarySpringApp.dao;

import org.gassion.LibrarySpringApp.model.Person;

import java.util.List;
import java.util.Optional;

public abstract class DAO<T> {
    public abstract Optional<T> getFromID(int id);
    public abstract List<T> getAll();
    public abstract void add(T t);
    public abstract void update(int id, T t);
    public abstract void delete(int id);

}
