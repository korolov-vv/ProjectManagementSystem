package ua.goit.service.projects;

import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dto.CompaniesDTO;
import ua.goit.dto.DevelopersDTO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.companies.CompaniesConverter;
import ua.goit.service.developers.DevelopersConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectsConverter {
    public static ProjectsDAO toProjectsDAO(ProjectsDTO projectsDTO) {
        Set<DevelopersDAO> developers = toDevelopersDAO(projectsDTO.getDevelopers());
        Set<CompaniesDAO> companies = toCompaniesDAO(projectsDTO.getCompanies());
        return new ProjectsDAO(projectsDTO.getProjectId(), projectsDTO.getProjectName(), projectsDTO.getStage(),
                projectsDTO.getTimePeriod(), projectsDTO.getCoast(), projectsDTO.getNumberOfDevelopers(),
                projectsDTO.getDateOfBeginning(), developers, companies);
    }

    public static ProjectsDTO fromProjectsDAO(ProjectsDAO projectsDAO) {
        Set<DevelopersDTO> developers = fromDevelopersDAO(projectsDAO.getDevelopers());
        Set<CompaniesDTO> companies = fromCompaniesDAO(projectsDAO.getCompanies());
        return new ProjectsDTO(projectsDAO.getProjectId(), projectsDAO.getProjectName(), projectsDAO.getStage(),
                projectsDAO.getTimePeriod(), projectsDAO.getCoast(), projectsDAO.getNumberOfDevelopers(),
                projectsDAO.getDateOfBeginning(), developers, companies);
    }

    private static Set<DevelopersDAO> toDevelopersDAO(Set<DevelopersDTO> developersDTOSet) {
        if(developersDTOSet == null) {
            return new HashSet<>();
        }else return developersDTOSet.stream()
                .map(DevelopersConverter::toDevelopersDAO)
                .collect(Collectors.toSet());
    }

    private static Set<CompaniesDAO> toCompaniesDAO(Set<CompaniesDTO> companiesDTOSet) {
        if(companiesDTOSet == null) {
            return new HashSet<>();
        }else return companiesDTOSet.stream()
                .map(CompaniesConverter::toCompaniesDAO)
                .collect(Collectors.toSet());
    }

    private static Set<DevelopersDTO> fromDevelopersDAO(Set<DevelopersDAO> developersDAOSet) {
        if(developersDAOSet == null) {
            return new HashSet<>();
        }else return developersDAOSet.stream()
                .map(DevelopersConverter::fromDevelopersDAO)
                .collect(Collectors.toSet());
    }

    private static Set<CompaniesDTO> fromCompaniesDAO(Set<CompaniesDAO> companiesDAOSet) {
        if(companiesDAOSet == null) {
            return new HashSet<>();
        }else return companiesDAOSet.stream()
                .map(CompaniesConverter::fromCompaniesDAO)
                .collect(Collectors.toSet());
    }
}
