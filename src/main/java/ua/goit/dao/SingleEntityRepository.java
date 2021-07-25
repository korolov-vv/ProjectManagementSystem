package ua.goit.dao;

import java.util.List;
import java.util.Optional;

public interface SingleEntityRepository<T> extends Repository<T> {
    List<T> findAll();

    Optional<T> findByUniqueParameter(String nameParameter, String value);

    void deleteByParameter(String nameParameter, String name);
}
