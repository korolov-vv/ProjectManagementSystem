package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.dao.model.DevelopersDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DevelopersRepository implements SingleEntityRepository<DevelopersDAO> {

    private static final Logger LOG = LoggerFactory.getLogger(DevelopersRepository.class);

    SessionFactory sessionFactory;

    private static final String SELECT_ALL_DEVELOPERS = "FROM DevelopersDAO AS d";

    private static final String SELECT_DEVELOPER_BY_EMAIL = "FROM DevelopersDAO d WHERE d.developerEmail = :developerEmail";

    public DevelopersRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public DevelopersDAO findById(int id) {
        DevelopersDAO developersDAO = new DevelopersDAO();
        try (Session session = sessionFactory.openSession()) {
            developersDAO = session.get(DevelopersDAO.class, id);
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return developersDAO;
    }

    @Override
    public void create(DevelopersDAO developersDAO) {
        createOrUpdate(developersDAO);
    }

    @Override
    public void update(DevelopersDAO developersDAO) {
        createOrUpdate(developersDAO);
    }

    @Override
    public void delete(String email) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            DevelopersDAO developersDAO = session.get(DevelopersDAO.class, email);
            if (developersDAO != null) {
                session.delete(developersDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            DevelopersDAO developersDAO = session.get(DevelopersDAO.class, id);
            if (developersDAO != null) {
                session.delete(developersDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public DevelopersDAO findByUniqueValue(String email) {
        List<DevelopersDAO> developersDAOList = new ArrayList<>();
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<DevelopersDAO> query = session.createQuery(SELECT_DEVELOPER_BY_EMAIL, DevelopersDAO.class);
            query.setParameter("developerEmail", email);
            developersDAOList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return developersDAOList.get(0);
    }

    @Override
    public List<DevelopersDAO> findAll() {
        List<DevelopersDAO> developersDAOList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<DevelopersDAO> query = session.createQuery(SELECT_ALL_DEVELOPERS,
                    DevelopersDAO.class);
            LOG.debug("Executing query: " + query.getQueryString());
            developersDAOList = query.getResultList();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug("Unable to execute query: " + ex.getMessage());
            ex.printStackTrace();
        }
        return developersDAOList;
    }

    private void createOrUpdate(DevelopersDAO developersDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(developersDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }
}
