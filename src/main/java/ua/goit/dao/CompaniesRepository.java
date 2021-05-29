package ua.goit.dao;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.model.CompaniesDAO;
import ua.goit.service.companies.CompaniesConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompaniesRepository implements Repository<CompaniesDAO> {
    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO companies (company_id, company_name, number_of_developers" +
            "VALUES (default, ?, ?);";
    private static final String SELECT_COMPANIY_BY_ID = "SELECT company_id, company_name, number_of_developers" +
            "FROM companies WHERE company_id = ?;";
    private static final String UPDATE = "UPDATE companies SET company_name=?, number_of_developers=?" +
            "WHERE company_id=?;";
    private static final String DELETE = "DELETE FROM companies WHERE company_id=?;";

    private static final String SELECT_COMPANIY_BY_NAME = "SELECT company_id, company_name, number_of_developers" +
            "FROM companies WHERE company_name = ?;";

    public CompaniesRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public CompaniesDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANIY_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CompaniesConverter.toCompany(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public CompaniesDAO findByUniqueValue(String companyName) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANIY_BY_NAME)) {
            preparedStatement.setString(1, companyName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CompaniesConverter.toCompany(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void create(CompaniesDAO companiesDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, companiesDAO.getCompanyName());
            preparedStatement.setInt(2, companiesDAO.getNumberOfDevelopers());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(CompaniesDAO companiesDAO) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, companiesDAO.getCompanyName());
            preparedStatement.setInt(2, companiesDAO.getNumberOfDevelopers());
            preparedStatement.setLong(3, companiesDAO.getCompanyId());
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
