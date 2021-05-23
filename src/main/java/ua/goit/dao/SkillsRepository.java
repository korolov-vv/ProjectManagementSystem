package ua.goit.dao;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.service.skills.SkillsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillsRepository implements Repository<SkillsDAO> {
    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSRT INTO skills (developer_id, stack, level)" +
            "VALUES (?, ?, ?, ?);";
    private static final String SELECT_SKILLS_BY_DEVELOPER_ID = "SELECT developer_id, stack, level" +
            "FROM skills WHERE developer_id=?;";
    private static final String UPDATE = "UPDATE skills SET developer_id=?, stack=?, level=?)" +
            "WHERE record_id=?;";
    private static final String DELETE = "DELETE FROM skills WHERE record_id=?;";

    public SkillsRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public SkillsDAO findById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(SELECT_SKILLS_BY_DEVELOPER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return SkillsConverter.toSkill(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(SkillsDAO skillsDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(INSERT)) {
            preparedStatement.setInt(1, skillsDAO.getRecordId());
            preparedStatement.setInt(2, skillsDAO.getDeveloperId());
            preparedStatement.setString(3, skillsDAO.getSatck());
            preparedStatement.setString(4, skillsDAO.getLevel());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(SkillsDAO skillsDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, skillsDAO.getRecordId());
            preparedStatement.setInt(2, skillsDAO.getDeveloperId());
            preparedStatement.setString(3, skillsDAO.getSatck());
            preparedStatement.setString(4, skillsDAO.getLevel());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
