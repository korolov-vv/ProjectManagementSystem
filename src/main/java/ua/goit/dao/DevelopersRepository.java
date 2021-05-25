package ua.goit.dao;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.model.DevelopersDAO;
import ua.goit.service.developers.DevelopersConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersRepository implements Repository<DevelopersDAO> {
    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO developers (developer_id, first_name, last_name, " +
            "gender, age, experience_in_years, company_id, project_id, salary)" +
            "VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_DEVELOPERS_BY_ID = "SELECT developer_id, first_name, last_name, " +
            "gender, age, experience_in_years, company_id, project_id, salary" +
            "FROM developers WHERE developer_id = ?;";
    private static final String UPDATE = "UPDATE developers SET first_name=?, last_name=?, " +
            "gender=?, age=?, experience_in_years=?, company_id=?, project_id=?, salary=?" +
            "WHERE developer_id=?;";
    private static final String DELETE = "DELETE FROM developers WHERE developer_id=?;";

    public DevelopersRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public DevelopersDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return DevelopersConverter.toDeveloper(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void create(DevelopersDAO developersDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, developersDAO.getFirstName());
            preparedStatement.setString(2, developersDAO.getLastName());
            preparedStatement.setString(3, developersDAO.getGender());
            preparedStatement.setInt(4, developersDAO.getAge());
            preparedStatement.setInt(5, developersDAO.getExperienceInYears());
            preparedStatement.setInt(6, developersDAO.getCompanyId());
            preparedStatement.setInt(7, developersDAO.getProjectId());
            preparedStatement.setInt(8, developersDAO.getSalary());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DevelopersDAO developersDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, developersDAO.getFirstName());
            preparedStatement.setString(2, developersDAO.getLastName());
            preparedStatement.setString(3, developersDAO.getGender());
            preparedStatement.setInt(4, developersDAO.getAge());
            preparedStatement.setInt(5, developersDAO.getExperienceInYears());
            preparedStatement.setInt(6, developersDAO.getCompanyId());
            preparedStatement.setInt(7, developersDAO.getProjectId());
            preparedStatement.setInt(8, developersDAO.getSalary());
            preparedStatement.setLong(9, developersDAO.getDeveloperId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
