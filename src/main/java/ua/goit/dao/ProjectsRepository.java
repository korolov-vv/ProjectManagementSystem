package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.dao.model.ProjectsDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectsRepository implements SingleEntityRepository<ProjectsDAO> {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectsRepository.class);

    private final SessionFactory sessionFactory;

    private static final String SELECT_PROJECT_BY_NAME = "FROM ProjectsDAO p WHERE p.projectName = :projectName";
    private static final String SELECT_ALL_PROJECTS = "FROM ProjectsDAO AS p";

    public ProjectsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProjectsDAO findById(int id) {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        try (Session session = sessionFactory.openSession()) {
            projectsDAO = session.get(ProjectsDAO.class, id);
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return projectsDAO;
    }

    @Override
    public ProjectsDAO findByUniqueValue(String projectName) {
        List<ProjectsDAO> projectsDAOList = new ArrayList<>();
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<ProjectsDAO> query = session.createQuery(SELECT_PROJECT_BY_NAME, ProjectsDAO.class);
            query.setParameter("projectName", projectName);
            projectsDAOList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return projectsDAOList.get(0);
    }

    @Override
    public void create(ProjectsDAO projectsDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(projectsDAO);
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
    public void update(ProjectsDAO projectsDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(projectsDAO);
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
    public void delete(int id) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            ProjectsDAO projectsDAO = session.get(ProjectsDAO.class, id);
            if (projectsDAO != null) {
                session.delete(projectsDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String name) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            ProjectsDAO projectsDAO = session.get(ProjectsDAO.class, name);
            if (projectsDAO != null) {
                session.delete(projectsDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProjectsDAO> findAll() {
        List<ProjectsDAO> projectsDAOList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<ProjectsDAO> query = session.createQuery(SELECT_ALL_PROJECTS, ProjectsDAO.class);
            projectsDAOList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return projectsDAOList;
    }
}
