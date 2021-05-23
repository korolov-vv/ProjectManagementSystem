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

    private static final String INSERT = "INSERT INTO developers (first_name, last_name, " +
            "gender, age, experience_in_years, company_id, project_id, salary)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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
    public DevelopersDAO findById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS_BY_ID)) {
            preparedStatement.setInt(1, id);
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
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, developersDAO.getFirstName());
            statement.setString(2, developersDAO.getLastName());
            statement.setString(3, developersDAO.getGender());
            statement.setInt(4, developersDAO.getAge());
            statement.setInt(5, developersDAO.getExperienceInYears());
            statement.setInt(6, developersDAO.getCompanyId());
            statement.setInt(5, developersDAO.getProjectId());
            statement.setInt(5, developersDAO.getSalary());
            statement.execute();
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
            preparedStatement.setInt(9, developersDAO.getDeveloperId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
