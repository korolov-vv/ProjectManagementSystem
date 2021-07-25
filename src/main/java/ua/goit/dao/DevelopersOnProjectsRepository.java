package ua.goit.dao;

import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.dao.model.DevelopersOnProjectsDAO;

import java.util.Objects;
import java.util.Optional;

public class DevelopersOnProjectsRepository implements MultiEntityRepository<DevelopersOnProjectsDAO> {

    private static final Logger LOG = LoggerFactory.getLogger(DevelopersOnProjectsRepository.class);

    SessionFactory sessionFactory;

    public DevelopersOnProjectsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(DevelopersOnProjectsDAO developersOnProjectsDAO) {
        try {
            createOrUpdate(developersOnProjectsDAO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DevelopersOnProjectsDAO developersOnProjectsDAO) {
        try {
            createOrUpdate(developersOnProjectsDAO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public DevelopersOnProjectsDAO findUniqueValue(int developerId, int projectId) {
        DevelopersOnProjectsDAO developersOnProjectsDAO = new DevelopersOnProjectsDAO();
        MultiIdentifierLoadAccess<DevelopersOnProjectsDAO> daoMultiIdentifierLoadAccess;
        try (Session session = sessionFactory.openSession()) {
            daoMultiIdentifierLoadAccess = session.byMultipleIds(DevelopersOnProjectsDAO.class);
            developersOnProjectsDAO = daoMultiIdentifierLoadAccess.multiLoad().get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return developersOnProjectsDAO;
    }

    @Override
    public void delete(int developerId) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            DevelopersOnProjectsDAO developersOnProjectsDAO = session.get(DevelopersOnProjectsDAO.class, developerId);
            if (developersOnProjectsDAO != null) {
                session.delete(developersOnProjectsDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<DevelopersOnProjectsDAO> findById(int id) {
        DevelopersOnProjectsDAO developersOnProjectsDAO = new DevelopersOnProjectsDAO();
        try (Session session = sessionFactory.openSession()) {
            developersOnProjectsDAO = session.get(DevelopersOnProjectsDAO.class, id);
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return Optional.ofNullable(developersOnProjectsDAO);
    }

    private void createOrUpdate(DevelopersOnProjectsDAO developersOnProjectsDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(developersOnProjectsDAO);
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
