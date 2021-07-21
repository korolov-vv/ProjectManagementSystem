package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.dao.model.CustomersDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomersRepository implements Repository<CustomersDAO> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomersRepository.class);

    private final SessionFactory sessionFactory;

    private static final String SELECT_CUSTOMERS_BY_NAME = "FROM CustomersDAO c WHERE c.customerName = :customerName";

    public CustomersRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CustomersDAO findById(int id) {
        CustomersDAO customersDAO = new CustomersDAO();
        try (Session session = sessionFactory.openSession()) {
             customersDAO = session.get(CustomersDAO.class, id);
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
        }
        return customersDAO;
    }

    @Override
    public CustomersDAO findByUniqueValue(String customerName) {
        List<CustomersDAO> customersDAOList = new ArrayList<>();
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<CustomersDAO> query = session.createQuery(SELECT_CUSTOMERS_BY_NAME, CustomersDAO.class);
            query.setParameter("customerName", customerName);
            customersDAOList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
        }
        return customersDAOList.get(0);
    }

    @Override
    public void create(CustomersDAO customersDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(customersDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            if(Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(CustomersDAO customersDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(customersDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(String name) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CustomersDAO customersDAO = session.get(CustomersDAO.class, name);
            if (customersDAO != null) {
                session.delete(customersDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
        }
    }
}
