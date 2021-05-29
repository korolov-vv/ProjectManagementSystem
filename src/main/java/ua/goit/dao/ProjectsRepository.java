package ua.goit.dao;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.service.projects.ProjectsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectsRepository implements Repository<ProjectsDAO> {
    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO projects (project_id, project_name, stage, time_period, coast)" +
            "VALUES (default, ?, ?, ?, ?);";
    private static final String SELECT_PROJECTS_BY_ID = "SELECT project_id, project_name, stage, time_period, coast" +
            "FROM projects WHERE project_id = ?;";
    private static final String UPDATE = "UPDATE projects SET project_name=?, stage=?, time_period=?, coast=?" +
            "WHERE project_id=?;";
    private static final String DELETE = "DELETE FROM projects WHERE project_id=?;";

    public ProjectsRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public ProjectsDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECTS_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ProjectsConverter.toProject(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ProjectsDAO findByUniqueValue(String firstName) {
        return null;
    }

    @Override
    public void create(ProjectsDAO projectsDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, projectsDAO.getProjectName());
            preparedStatement.setString(2, projectsDAO.getStage());
            preparedStatement.setInt(3, projectsDAO.getTimePeriod());
            preparedStatement.setInt(4, projectsDAO.getCoast());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(ProjectsDAO projectsDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, projectsDAO.getProjectName());
            preparedStatement.setString(2, projectsDAO.getStage());
            preparedStatement.setInt(3, projectsDAO.getTimePeriod());
            preparedStatement.setInt(4, projectsDAO.getCoast());
            preparedStatement.setLong(5, projectsDAO.getProjectId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECTS_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
