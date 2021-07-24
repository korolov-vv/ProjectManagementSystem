package ua.goit.dao;

public interface Repository<T> {

    void create(T entity);

    void update(T entity);

    void delete(int id);

    T findById(int id);
}
