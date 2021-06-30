package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.CustomersAndCompaniesDAO;
import ua.goit.service.customers.CustomersAndCompaniesConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersAndCompaniesRepository implements MultiEntityRepository<CustomersAndCompaniesDAO> {
    private final HikariDataSource connectionManager;

    private static final String INSERT = "INSERT INTO customers_and_companies (customer_id, company_id, project_id) " +
            "VALUES (?, ?, ?);";

    private static final String UPDATE = "UPDATE customers_and_companies SET customer_id=?, company_id=?, project_id=? " +
            "WHERE customer_id=? and company_id=? and project_id=?;";

    private static final String SELECT_UNIQUE_RECORD = "SELECT customer_id, company_id, project_id " +
            "FROM customers_and_companies " +
            "WHERE customer_id=? and company_id=? and project_id=?;";

    private static final String SELECT_FOR_PROJECT = "SELECT customer_id, company_id, project_id " +
            "FROM customers_and_companies " +
            "WHERE project_id=?;";

    private static final String DELETE_FOR_CUSTOMER = "DELETE FROM customers_and_companies WHERE customer_id=?;";

    private static final String DELETE_FOR_PROJECT = "DELETE FROM customers_and_companies WHERE project_id=?;";

    private static final String DELETE_FOR_COMPANY = "DELETE FROM customers_and_companies WHERE company_id=?;";

    private static final String DELETE_UNIQUE_RECORD = "DELETE FROM customers_and_companies " +
            "WHERE customer_id=? and company_id=? and project_id=?;";

    public CustomersAndCompaniesRepository(HikariDataSource connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void create(CustomersAndCompaniesDAO customersAndCompaniesDAO) {
        try {
            PreparedStatement preparedStatement = prepareStatement(customersAndCompaniesDAO, INSERT);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(CustomersAndCompaniesDAO customersAndCompaniesDAO) {
        try {
            PreparedStatement preparedStatement = prepareStatement(customersAndCompaniesDAO, UPDATE);
            preparedStatement.setLong(1, customersAndCompaniesDAO.getCompanyId());
            preparedStatement.setLong(2, customersAndCompaniesDAO.getCustomerId());
            preparedStatement.setLong(3, customersAndCompaniesDAO.getProjectId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUniqueRecord(long customerId, long companyId, long projectId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_UNIQUE_RECORD)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.setLong(2, companyId);
            preparedStatement.setLong(3, projectId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    public void deleteForCustomer(long customerId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOR_CUSTOMER)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteForCompany(long companyId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOR_COMPANY)) {
            preparedStatement.setLong(1, companyId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteForProject(long projectId) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOR_PROJECT)) {
            preparedStatement.setLong(1, projectId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CustomersAndCompaniesDAO findUniqueRecord(long companyId, long customerId, long projectId){
        ResultSet resultSet;
        CustomersAndCompaniesDAO customersAndCompaniesDAO = new CustomersAndCompaniesDAO();
        try (Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UNIQUE_RECORD)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.setLong(2, companyId);
            preparedStatement.setLong(3, projectId);
            resultSet = preparedStatement.executeQuery();
            customersAndCompaniesDAO = CustomersAndCompaniesConverter.toCustomerAndCompanies(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customersAndCompaniesDAO;
    }

    public CustomersAndCompaniesDAO findForProject (long projectId){
        ResultSet resultSet;
        CustomersAndCompaniesDAO customersAndCompaniesDAO = new CustomersAndCompaniesDAO();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOR_PROJECT)) {
            preparedStatement.setLong(1, projectId);
            resultSet = preparedStatement.executeQuery();
            customersAndCompaniesDAO = CustomersAndCompaniesConverter.toCustomerAndCompanies(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customersAndCompaniesDAO;
    }

    public PreparedStatement prepareStatement(CustomersAndCompaniesDAO customersAndCompaniesDAO, String statement) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setLong(1, customersAndCompaniesDAO.getCompanyId());
        preparedStatement.setLong(2, customersAndCompaniesDAO.getCustomerId());
        preparedStatement.setLong(3, customersAndCompaniesDAO.getProjectId());
        return preparedStatement;
    }
}
