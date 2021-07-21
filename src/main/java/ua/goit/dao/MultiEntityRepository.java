package ua.goit.dao;

public interface MultiEntityRepository<T> {

    void create(T entity);

    void update(T entity);

    T findUniqueValue(int firstEntityId, int secondEntityId);

    void delete(int entityId);
}
