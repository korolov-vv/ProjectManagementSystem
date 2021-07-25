package ua.goit.dao;

import java.util.Optional;

public interface Repository<T> {

    void create(T entity);

    void update(T entity);

    void delete(int id);

    Optional<T> findById(int id);
}
