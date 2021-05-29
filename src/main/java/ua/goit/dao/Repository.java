package ua.goit.dao;

public interface Repository<T> {
    T findById(long id);

    T findByEmail(String value);

    void create(T entity);

    void update(T entity);

    void delete(long id);
}
