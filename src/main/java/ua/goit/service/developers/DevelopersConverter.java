package ua.goit.service.developers;

import ua.goit.dto.DevelopersDTO;
import ua.goit.dao.model.DevelopersDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersConverter {
    public static DevelopersDAO toDeveloper(DevelopersDTO developersDTO) {
        return new DevelopersDAO(developersDTO.getDeveloperId(), developersDTO.getFirstName(),
                developersDTO.getLastName(), developersDTO.getGender(), developersDTO.getAge(),
                developersDTO.getExperienceInYears(), developersDTO.getCompanyId(),
                developersDTO.getProjectId(), developersDTO.getSalary());
    }

    public static DevelopersDTO fromDeveloper(DevelopersDAO developersDAO) {
        return new DevelopersDTO(developersDAO.getDeveloperId(), developersDAO.getFirstName(),
                developersDAO.getLastName(), developersDAO.getGender(), developersDAO.getAge(),
                developersDAO.getExperienceInYears(), developersDAO.getCompanyId(),
                developersDAO.getProjectId(), developersDAO.getSalary());
    }

    public static DevelopersDAO toDeveloper(ResultSet resultSet) throws SQLException {
        DevelopersDAO developersDAO = new DevelopersDAO();
        while (resultSet.next()) {
            developersDAO.setDeveloperId(resultSet.getLong("developer_id"));
            developersDAO.setFirstName(resultSet.getString("first_name"));
            developersDAO.setLastName(resultSet.getString("last_name"));
            developersDAO.setGender(resultSet.getString("gender"));
            developersDAO.setAge(resultSet.getInt("age"));
            developersDAO.setExperienceInYears(resultSet.getInt("experience_in_years"));
            developersDAO.setCompanyId(resultSet.getInt("company_id"));
            developersDAO.setProjectId(resultSet.getInt("project_id"));
            developersDAO.setSalary(resultSet.getInt("salary"));
        }
        return developersDAO;
    }
}
