package ua.goit.dao;

public interface MultiEntityRepository<T> extends Repository<T> {
    T findUniqueValue(int firstEntityId, int secondEntityId);
}