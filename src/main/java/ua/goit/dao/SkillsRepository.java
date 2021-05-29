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

    private static final String INSERT = "INSERT INTO skills (record_id, developer_id, stack, level)" +
            "VALUES (default, ?, ?, ?);";
    private static final String SELECT_SKILLS_BY_DEVELOPER_ID = "SELECT developer_id, stack, level" +
            "FROM skills WHERE developer_id=?;";
    private static final String UPDATE = "UPDATE skills SET developer_id=?, stack=?, level=?)" +
            "WHERE record_id=?;";
    private static final String DELETE = "DELETE FROM skills WHERE record_id=?;";

    public SkillsRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public SkillsDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(SELECT_SKILLS_BY_DEVELOPER_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return SkillsConverter.toSkill(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public SkillsDAO findByEmail(String value) {
        return null;
    }

    @Override
    public void create(SkillsDAO skillsDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(INSERT)) {
            preparedStatement.setLong(1, skillsDAO.getDeveloperId());
            preparedStatement.setString(2, skillsDAO.getSatck().toString());
            preparedStatement.setString(3, skillsDAO.getLevel().toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(SkillsDAO skillsDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, skillsDAO.getDeveloperId());
            preparedStatement.setString(2, skillsDAO.getSatck().toString());
            preparedStatement.setString(3, skillsDAO.getLevel().toString());
            preparedStatement.setLong(4, skillsDAO.getRecordId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
