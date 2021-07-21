package ua.goit.dao;

public interface Repository<T> {
    T findById(int id);

    T findByUniqueValue(String value);

    void create(T entity);

    void update(T entity);

    void delete(String value);
}
