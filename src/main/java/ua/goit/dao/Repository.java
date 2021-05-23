package ua.goit.dao;

public interface Repository<T> {
    T findById(Integer id);

    void create(T entity);

    void update(T entity);

    void delete(int id);
}
