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

public class DevelopersRepository implements Repository<DevelopersDAO> {

    private static final Logger LOG = LoggerFactory.getLogger(DevelopersRepository.class);

    SessionFactory sessionFactory;

    private static final String SELECT_ALL_DEVELOPERS = "FROM DevelopersDAO AS d";

    private static final String SELECT_DEVELOPER_BY_EMAIL = "FROM DevelopersDAO d WHERE d.developerEmail = :developerEmail";
/*
    private static final String SELECT_DEVELOPERS_ON_PROJECT = "SELECT d.developer_id, first_name, last_name, gender, " +
            "age, experience_in_years, company_id, salary, developer_email FROM developers as d " +
            "INNER JOIN developers_on_projects as dp on d.developer_id=dp.developer_id " +
            "WHERE project_id = :projectId";

    private static final String SELECT_DEVELOPERS_BY_STACK = "SELECT developer_id, first_name, last_name, gender, " +
            "age, experience_in_years, company_id, salary, d.developer_email " +
            "FROM developers d INNER JOIN skills as s on d.developer_email=s.developer_email " +
            "WHERE stack::text ILIKE ?;";

    private static final String SELECT_DEVELOPERS_BY_LEVEL = "SELECT developer_id, first_name, last_name, gender, " +
            "age, experience_in_years, company_id, salary, d.developer_email " +
            "FROM developers d INNER JOIN skills as s on d.developer_email=s.developer_email " +
            "WHERE level::text ILIKE ?;";*/

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

/*    public List<DevelopersDTO> selectDevelopersOnProject(int projectId) {
        List<DevelopersDAO> listDAO;
        List<DevelopersDTO> listOfDevelopers = new ArrayList<>();
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
           Query<DevelopersDAO> query = session.createQuery(
                   SELECT_DEVELOPERS_ON_PROJECT, DevelopersDAO.class
           );
           query.setParameter("projectId", projectId);
           listDAO = query.list();
            listOfDevelopers = listDAO.stream()
                    .map(DevelopersConverter::fromDeveloper)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfDevelopers;
    }

    public List<DevelopersDTO> selectDevelopersByStack(String stack) {
        return selectDevelopersByParametr(SELECT_DEVELOPERS_BY_STACK, stack);
    }

    public List<DevelopersDTO> selectDevelopersByLevel(String level) {
        return selectDevelopersByParametr(SELECT_DEVELOPERS_BY_LEVEL, level);
    }

    public PreparedStatement prepareStatment(DevelopersDAO developersDAO, String statement) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, developersDAO.getFirstName());
        preparedStatement.setString(2, developersDAO.getLastName());
        preparedStatement.setString(3, developersDAO.getGender());
        preparedStatement.setInt(4, developersDAO.getAge());
        preparedStatement.setInt(5, developersDAO.getExperienceInYears());
        preparedStatement.setInt(6, developersDAO.getCompanyId());
        preparedStatement.setInt(7, developersDAO.getSalary());
        preparedStatement.setString(8, developersDAO.getDeveloperEmail());
        return preparedStatement;
    }*/
/*
    public List<DevelopersDTO> selectDevelopersByParametr(String query, String value) {
        ResultSet resultSet;
        List<DevelopersDAO> listDAO;
        List<DevelopersDTO> listOfDevelopers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, value);
            resultSet = preparedStatement.executeQuery();
            listDAO = DevelopersConverter.toDevelopersList(resultSet);
            listOfDevelopers = listDAO.stream()
                    .map(DevelopersConverter::fromDeveloper)
                    .collect(Collectors.toList());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOfDevelopers;
    }*/
}
