package ua.goit.service.projects;

import ua.goit.dao.model.CompanyDAO;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.companies.CompaniesConverter;
import ua.goit.service.developers.DevelopersConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectsConverter {
    public static ProjectDAO toProjectsDAO(ProjectDTO projectDTO) {
        Set<DeveloperDAO> developers = toDevelopersDAO(projectDTO.getDevelopers());
        Set<CompanyDAO> companies = toCompaniesDAO(projectDTO.getCompanies());
        return new ProjectDAO(projectDTO.getProjectId(), projectDTO.getProjectName(), projectDTO.getStage(),
                projectDTO.getTimePeriod(), projectDTO.getCoast(), projectDTO.getNumberOfDevelopers(),
                projectDTO.getDateOfBeginning(), developers, companies);
    }

    public static ProjectDTO fromProjectsDAO(ProjectDAO projectDAO) {
        Set<DeveloperDTO> developers = fromDevelopersDAO(projectDAO.getDevelopers());
        Set<CompanyDTO> companies = fromCompaniesDAO(projectDAO.getCompanies());
        return new ProjectDTO(projectDAO.getProjectId(), projectDAO.getProjectName(), projectDAO.getStage(),
                projectDAO.getTimePeriod(), projectDAO.getCoast(), projectDAO.getNumberOfDevelopers(),
                projectDAO.getDateOfBeginning(), developers, companies);
    }

    private static Set<DeveloperDAO> toDevelopersDAO(Set<DeveloperDTO> developerDTOSet) {
        if(developerDTOSet == null) {
            return new HashSet<>();
        }else return developerDTOSet.stream()
                .map(DevelopersConverter::toDevelopersDAO)
                .collect(Collectors.toSet());
    }

    private static Set<CompanyDAO> toCompaniesDAO(Set<CompanyDTO> companyDTOSet) {
        if(companyDTOSet == null) {
            return new HashSet<>();
        }else return companyDTOSet.stream()
                .map(CompaniesConverter::toCompaniesDAO)
                .collect(Collectors.toSet());
    }

    private static Set<DeveloperDTO> fromDevelopersDAO(Set<DeveloperDAO> developerDAOSet) {
        if(developerDAOSet == null) {
            return new HashSet<>();
        }else return developerDAOSet.stream()
                .map(DevelopersConverter::fromDevelopersDAO)
                .collect(Collectors.toSet());
    }

    private static Set<CompanyDTO> fromCompaniesDAO(Set<CompanyDAO> companyDAOSet) {
        if(companyDAOSet == null) {
            return new HashSet<>();
        }else return companyDAOSet.stream()
                .map(CompaniesConverter::fromCompaniesDAO)
                .collect(Collectors.toSet());
    }
}
