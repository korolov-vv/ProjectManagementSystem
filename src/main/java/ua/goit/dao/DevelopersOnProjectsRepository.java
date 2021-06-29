package ua.goit.dao;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.model.DevelopersOnProjectsDAO;
import ua.goit.service.developers.DevelopersOnProjectsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersOnProjectsRepository implements MultiEntityRepository<DevelopersOnProjectsDAO> {

    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO developers_on_projects (developer_id, project_id)" +
            "VALUES (?, ?);";

    private static final String UPDATE = "UPDATE developers_on_projects SET developer_id=?, project_id=?" +
            "WHERE developer_id=? and project_id=?;";

    private static final String DELETE_FOR_DEVELOPERS = "DELETE FROM developers_on_projects WHERE developer_id=?;";

    private static final String DELETE_FOR_PROJECTS = "DELETE FROM developers_on_projects WHERE project_id=?;";

    private static final String DELETE_UNIQUE_VALUE = "DELETE FROM developers_on_projects WHERE developer_id=? and project_id=?;";

    private static final String SELECT_DEVELOPER_ON_PROJECT = "SELECT developer_id, project_id " +
            "FROM developers_on_projects " +
            "WHERE developer_id=? and project_id=?;";

    public DevelopersOnProjectsRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void create(DevelopersOnProjectsDAO developersOnProjectsDAO) {
        try {
            PreparedStatement preparedStatement = prepareStatement(developersOnProjectsDAO, INSERT);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DevelopersOnProjectsDAO developersOnProjectsDAO) {
        try {
            PreparedStatement preparedStatement = prepareStatement(developersOnProjectsDAO, UPDATE);
            preparedStatement.setString(1, String.valueOf(developersOnProjectsDAO.getDeveloperId()));
            preparedStatement.setString(2, String.valueOf(developersOnProjectsDAO.getProjectId()));
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DevelopersOnProjectsDAO findUniqueValue(long developerId, long projectId) {
        ResultSet resultSet;
        DevelopersOnProjectsDAO developersOnProjectsDAO = new DevelopersOnProjectsDAO();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPER_ON_PROJECT)) {
            preparedStatement.setString(1, String.valueOf(developerId));
            preparedStatement.setString(2, String.valueOf(projectId));
            resultSet = preparedStatement.executeQuery();
            developersOnProjectsDAO = DevelopersOnProjectsConverter.toDeveloperOnProject(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return developersOnProjectsDAO;
    }

    public void deleteUniqueRecord(long developerId, long projectId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_UNIQUE_VALUE)) {
            preparedStatement.setString(1, String.valueOf(developerId));
            preparedStatement.setString(2, String.valueOf(projectId));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteForDevelopers(String developerId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOR_DEVELOPERS)) {
            preparedStatement.setString(1, developerId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteForProjects(String projectId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOR_PROJECTS)) {
            preparedStatement.setString(1, projectId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(DevelopersOnProjectsDAO developersOnProjectsDAO, String statement) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, String.valueOf(developersOnProjectsDAO.getDeveloperId()));
        preparedStatement.setString(2, String.valueOf(developersOnProjectsDAO.getProjectId()));
        return preparedStatement;
    }
}
