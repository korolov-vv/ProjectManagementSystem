package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.exception.DatabaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SingleRepositoryImplementation<T> implements SingleEntityRepository<T> {

    private final Logger logger;
    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public SingleRepositoryImplementation(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
        this.logger = LoggerFactory.getLogger(entityClass);
    }

    @Override
    public void create(T entity) {
        createOrUpdate(entity);
    }

    @Override
    public void update(T entity) {
        createOrUpdate(entity);
    }

    @Override
    public void delete(int id) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if(findById(id).isPresent()) {
                T entityForDelete = findById(id).get();
                session.delete(entityForDelete);
            }
            transaction.commit();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<T> findById(int id) {
        T entity = null;
        try (Session session = sessionFactory.openSession()) {
            entity = session.get(entityClass, id);
        } catch (Exception ex) {
            logger.debug(String.format("findById id = %s ", id) + ex.getMessage() + " cause: " + ex.getCause());
            ex.printStackTrace();
        }
        if(entity == null) {
            return Optional.empty();
        }else {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        List<T> entitiesList = new ArrayList<>();
        String queryString = "FROM " + entityClass.getName() + " entity";
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<T> query = session.createQuery(queryString,
                    entityClass);
            logger.debug("Executing query: " + query.getQueryString());
            entitiesList = query.getResultList();
            transaction.commit();
        } catch (Exception ex) {
            logger.debug("Unable to execute query: " + ex.getMessage() + " cause: " + ex.getCause());
            ex.printStackTrace();
        }
        return entitiesList;
    }

    @Override
    public Optional<T> findByUniqueParameter(String nameParameter, String value) {
        List<T> entityList = new ArrayList<>();
        String queryString = createFindByNameQuery(nameParameter);
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<T> query = session.createQuery(queryString, entityClass);
            query.setParameter(nameParameter, value);
            logger.debug("Executing query: " + query.getQueryString());
            entityList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            logger.debug(String.format("Cannot find by %s = %s", nameParameter, value) + ex.getMessage() + " cause: " + ex.getCause());
            ex.printStackTrace();
        }
        if(entityList.size() != 0) {
            return Optional.of(entityList.get(0));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByParameter(String nameParameter, String name) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if(findByUniqueParameter(nameParameter, name).isPresent()) {
                T entity = findByUniqueParameter(nameParameter, name).get();
                session.delete(entity);
            }else throw new DatabaseException(String.format("The object with %s %s not found", nameParameter, name));
            transaction.commit();
        } catch (Exception ex) {
            logger.debug(String.format("Cannot delete by %s = %s", nameParameter, name) + ex.getMessage() + " cause: " + ex.getCause());
            ex.printStackTrace();
        }
    }

    private String createFindByNameQuery(String nameParameter) {
        return "FROM " + entityClass.getName() + " entity WHERE entity." + nameParameter + " = :" + nameParameter;
    }

    private void createOrUpdate(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }
}
