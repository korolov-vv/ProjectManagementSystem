package ua.goit.repository;

import ua.goit.repository.model.DevelopersDAO;

public interface Repository<T> {
    T findById(Integer id);
    void create(T entity);

    void update(DevelopersDAO locationDAO);

    void delete(int id);
}
