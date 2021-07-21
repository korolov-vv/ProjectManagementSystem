package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.dao.model.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SkillsRepository implements Repository<SkillsDAO> {

    private static final Logger LOG = LoggerFactory.getLogger(SkillsRepository.class);

    private final SessionFactory sessionFactory;

    private static final String SELECT_SKILLS_BY_DEVELOPER_EMAIL = "FROM SkillsDAO s WHERE s.developerEmail = :developerEmail";

    private static final String SELECT_SKILLS_OF_DEVELOPER_BY_EMAIL = "FROM SkillsDAO s " +
            "WHERE s.developerEmail = :developerEmail AND s.stack = :stack";

    public SkillsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SkillsDAO findById(int id) {
        SkillsDAO skillsDAO = new SkillsDAO();
        try (Session session = sessionFactory.openSession()) {
            skillsDAO = session.get(SkillsDAO.class, id);
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return skillsDAO;
    }

    @Override
    public SkillsDAO findByUniqueValue(String email) {
        List<SkillsDAO> skillsDAOList = new ArrayList<>();
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<SkillsDAO> query = session.createQuery(SELECT_SKILLS_BY_DEVELOPER_EMAIL, SkillsDAO.class);
            query.setParameter("developerEmail", email);
            skillsDAOList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return skillsDAOList.get(0);
    }

    public SkillsDAO findSkillOfDeveloperByEmail(String email, Stack stack) {
        List<SkillsDAO> skillsDAOList;
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<SkillsDAO> query = session.createQuery(SELECT_SKILLS_OF_DEVELOPER_BY_EMAIL, SkillsDAO.class);
            query.setParameter("developerEmail", email);
            query.setParameter("stack", stack);
            skillsDAOList = query.list();
            transaction.commit();
            return skillsDAOList.get(0);
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return new SkillsDAO();
    }

    @Override
    public void create(SkillsDAO skillsDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(skillsDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(SkillsDAO skillsDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(skillsDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(String id) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            SkillsDAO skillsDAO = session.get(SkillsDAO.class, id);
            if (skillsDAO != null) {
                session.delete(skillsDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }


}
