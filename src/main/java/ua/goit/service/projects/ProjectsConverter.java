package ua.goit.service.projects;

import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.companies.CompaniesConverter;
import ua.goit.service.developers.DevelopersConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectsConverter {
    public static ProjectDAO toProjectsDAO(ProjectDTO projectDTO) {
        ProjectDAO projectDAO = getProjectDAO(projectDTO);
        projectDAO.setDevelopers(DevelopersConverter.toDevelopersDAOSet(projectDTO.getDevelopers()));
        projectDAO.setCompanies(CompaniesConverter.toCompaniesDAOSet(projectDTO.getCompanies()));
        return projectDAO;
    }

    public static ProjectDTO fromProjectsDAO(ProjectDAO projectDAO) {
        ProjectDTO projectDTO = getProjectDTO(projectDAO);
        projectDTO.setDevelopers(DevelopersConverter.fromDevelopersDAOSet(projectDAO.getDevelopers()));
        projectDTO.setCompanies(CompaniesConverter.fromCompaniesDAOSet(projectDAO.getCompanies()));
        return projectDTO;
    }

    public static Set<ProjectDTO> fromProjectsDAOSet(Set<ProjectDAO> projectDAOSet) {
        if(projectDAOSet == null) {
            return new HashSet<>();
        }else return projectDAOSet.stream()
                .map(ProjectsConverter::getProjectDTO)
                .collect(Collectors.toSet());
    }

    public static Set<ProjectDAO> toProjectsDAOSet(Set<ProjectDTO> projectDTOSet) {
        if(projectDTOSet == null) {
            return new HashSet<>();
        }else return projectDTOSet.stream()
                .map(ProjectsConverter::getProjectDAO)
                .collect(Collectors.toSet());
    }

    private static ProjectDAO getProjectDAO(ProjectDTO projectDTO) {
        return new ProjectDAO(projectDTO.getProjectId(), projectDTO.getProjectName(), projectDTO.getStage(),
                projectDTO.getTimePeriod(), projectDTO.getCoast(), projectDTO.getNumberOfDevelopers(),
                projectDTO.getDateOfBeginning());
    }

    private static ProjectDTO getProjectDTO(ProjectDAO projectDAO) {
        return new ProjectDTO(projectDAO.getProjectId(), projectDAO.getProjectName(), projectDAO.getStage(),
                projectDAO.getTimePeriod(), projectDAO.getCoast(), projectDAO.getNumberOfDevelopers(),
                projectDAO.getDateOfBeginning());
    }
}
