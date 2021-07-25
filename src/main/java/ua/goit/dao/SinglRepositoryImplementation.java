package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.dao.model.CompanyDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SinglRepositoryImplementation<T> implements SingleEntityRepository<T> {

    private static Logger logger;
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    public SinglRepositoryImplementation(SessionFactory sessionFactory, Class<T> entityClass) {
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
            logger.debug("findById error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        List<T> entitiesList = new ArrayList<>();
        String queryString = "FROM " + entityClass.getName() + " entity";
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<T> query = session.createQuery(queryString,
                    entityClass);
            logger.debug("Executing query: " + query.getQueryString());
            entitiesList = query.getResultList();
            transaction.commit();
        } catch (Exception ex) {
            logger.debug("Unable to execute query: " + ex.getMessage());
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
            query.setParameter("companyName", value);
            entityList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return Optional.ofNullable(entityList.get(0));
    }

    @Override
    public void deleteByParameter(String nameParameter, String name) {
        String queryString = createDeleteQuery(nameParameter);
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CompanyDAO companyDAO = session.get(CompanyDAO.class, name);
            if (companyDAO != null) {
                session.delete(companyDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String createFindByNameQuery(String nameParameter) {
        return "FROM " + entityClass.getName() + " entity WHERE entity." + nameParameter + " = :" + nameParameter;
    }

    private String createDeleteQuery(String nameParameter) {
        return "DELETE FROM " + entityClass.getName() + " entity  where entity." + nameParameter + " = :" + nameParameter;
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
