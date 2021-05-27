package ua.goit.service.projects;

import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dto.ProjectsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectsConverter {
    public static ProjectsDAO toProject(ProjectsDTO projectsDTO) {
        return new ProjectsDAO(projectsDTO.getProjectId(), projectsDTO.getCompanyId(), projectsDTO.getCustomerId(),
                projectsDTO.getProjectName(), projectsDTO.getStage(), projectsDTO.getTimePeriod(),
                projectsDTO.getCoast());
    }

    public static ProjectsDTO fromProject(ProjectsDAO projectsDAO) {
        return new ProjectsDTO(projectsDAO.getProjectId(), projectsDAO.getCompanyId(), projectsDAO.getCustomerId(),
                projectsDAO.getProjectName(), projectsDAO.getStage(), projectsDAO.getTimePeriod(),
                projectsDAO.getCoast());
    }

    public static ProjectsDAO toProject(ResultSet resultSet) throws SQLException {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        while (resultSet.next()) {
            projectsDAO.setProjectId(resultSet.getLong("project_id"));
            projectsDAO.setCompanyId(resultSet.getLong("company_id"));
            projectsDAO.setCustomerId(resultSet.getLong("customer_id"));
            projectsDAO.setProjectName(resultSet.getString("project_name"));
            projectsDAO.setStage(resultSet.getString("stage"));
            projectsDAO.setTimePeriod(resultSet.getInt("time_period"));
            projectsDAO.setCoast(resultSet.getInt("coast"));
        }
        return projectsDAO;
    }
}
