package ua.goit.dao;

import java.util.List;

public interface SingleEntityRepository<T> extends Repository<T> {
    List<T> findAll();

    T findByUniqueValue(String value);

    void delete(String name);
}
