package ua.goit.service.companies;

import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dto.CompaniesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompaniesConverter {
    public static CompaniesDAO toCompany(CompaniesDTO companiesDTO) {
        return new CompaniesDAO(companiesDTO.getCompanyId(), companiesDTO.getCompanyName(),
                companiesDTO.getNumberOfDevelopers());
    }

    public static CompaniesDTO fromCompany(CompaniesDAO companiesDAO) {
        return new CompaniesDTO(companiesDAO.getCompanyId(), companiesDAO.getCompanyName(),
                companiesDAO.getNumberOfDevelopers());
    }

    public static CompaniesDAO toCompany(ResultSet resultSet) throws SQLException {
        CompaniesDAO companiesDAO = new CompaniesDAO();
        while (resultSet.next()) {
            companiesDAO.setCompanyId(resultSet.getLong("company_id"));
            companiesDAO.setCompanyName(resultSet.getString("company_name"));
            companiesDAO.setNumberOfDevelopers(resultSet.getInt("number_of_developers"));
        }
        return companiesDAO;
    }
}
