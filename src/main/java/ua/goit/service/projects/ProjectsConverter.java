package ua.goit.service.projects;

import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.ProjectDTO;

public class ProjectsConverter {
    public static ProjectDAO toProjectsDAO(ProjectDTO projectDTO) {
        return new ProjectDAO(projectDTO.getProjectId(), projectDTO.getProjectName(), projectDTO.getStage(),
                projectDTO.getTimePeriod(), projectDTO.getCoast(), projectDTO.getNumberOfDevelopers(),
                projectDTO.getDateOfBeginning());
    }

    public static ProjectDTO fromProjectsDAO(ProjectDAO projectDAO) {
        return new ProjectDTO(projectDAO.getProjectId(), projectDAO.getProjectName(), projectDAO.getStage(),
                projectDAO.getTimePeriod(), projectDAO.getCoast(), projectDAO.getNumberOfDevelopers(),
                projectDAO.getDateOfBeginning());
    }
}
