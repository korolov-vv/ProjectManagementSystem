package ua.goit.dao;

import ua.goit.dao.model.DevelopersDAO;

public interface Repository<T> {
    T findById(Integer id);
    void create(T entity);

    void update(DevelopersDAO locationDAO);

    void delete(int id);
}
