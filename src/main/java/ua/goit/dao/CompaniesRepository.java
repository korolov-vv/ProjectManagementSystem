package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.goit.dao.model.CompaniesDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompaniesRepository implements Repository<CompaniesDAO> {
    private final SessionFactory sessionFactory;

    private static final String SELECT_ALL_COMPANIES = "FROM CompaniesDAO";

    public CompaniesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CompaniesDAO findById(long id) {
        CompaniesDAO companiesDAO = new CompaniesDAO();
        try (Session session = sessionFactory.openSession()) {
            companiesDAO = session.get(CompaniesDAO.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return companiesDAO;
    }

    @Override
    public CompaniesDAO findByUniqueValue(String companyName) {
        CompaniesDAO companiesDAO = new CompaniesDAO();
        try (Session session = sessionFactory.openSession()) {
            companiesDAO = session.get(CompaniesDAO.class, companyName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return companiesDAO;
    }

    @Override
    public void create(CompaniesDAO companiesDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(companiesDAO);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(CompaniesDAO companiesDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(companiesDAO);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
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
            CompaniesDAO companiesDAO = session.get(CompaniesDAO.class, name);
            if (companiesDAO != null) {
                session.delete(companiesDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<CompaniesDAO> findAllCompanies() {
        List<CompaniesDAO> companiesDAOList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query<CompaniesDAO> query = session.createQuery(SELECT_ALL_COMPANIES, CompaniesDAO.class);
            companiesDAOList = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return companiesDAOList;
    }
}
