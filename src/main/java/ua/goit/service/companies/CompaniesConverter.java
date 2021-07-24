package ua.goit.service.companies;

import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dao.model.CustomersDAO;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dto.CompaniesDTO;
import ua.goit.dto.CustomersDTO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.customers.CustomersConverter;
import ua.goit.service.projects.ProjectsConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CompaniesConverter {
    public static CompaniesDAO toCompaniesDAO(CompaniesDTO companiesDTO) {
        Set<ProjectsDAO> projects = toProjectsDAO(companiesDTO.getProjects());
        Set<CustomersDAO> customers = toCustomersDAO(companiesDTO.getCustomers());
        return new CompaniesDAO(companiesDTO.getCompanyId(), companiesDTO.getCompanyName(),
                companiesDTO.getNumberOfDevelopers(), projects, customers);
    }

    public static CompaniesDTO fromCompaniesDAO(CompaniesDAO companiesDAO) {
        Set<ProjectsDTO> projects = fromProjectsDAO(companiesDAO.getProjects());
        Set<CustomersDTO> customers = fromCustomersDAO(companiesDAO.getCustomers());
        return new CompaniesDTO(companiesDAO.getCompanyId(), companiesDAO.getCompanyName(),
                companiesDAO.getNumberOfDevelopers(), projects, customers);
    }

    private static Set<ProjectsDAO> toProjectsDAO(Set<ProjectsDTO> projectsDTOSet) {
        if(projectsDTOSet == null) {
            return new HashSet<>();
        }else return projectsDTOSet.stream()
                .map(ProjectsConverter::toProjectsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<CustomersDAO> toCustomersDAO(Set<CustomersDTO> customersDTOSet) {
        if(customersDTOSet == null) {
            return new HashSet<>();
        }else return customersDTOSet.stream()
                .map(CustomersConverter::toCustomerDAO)
                .collect(Collectors.toSet());
    }

    private static Set<ProjectsDTO> fromProjectsDAO(Set<ProjectsDAO> projectsDAOSet) {
        if(projectsDAOSet == null) {
            return new HashSet<>();
        }else return projectsDAOSet.stream()
                .map(ProjectsConverter::fromProjectsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<CustomersDTO> fromCustomersDAO(Set<CustomersDAO> customersDAOSet) {
        if(customersDAOSet == null) {
            return new HashSet<>();
        }else return customersDAOSet.stream()
                .map(CustomersConverter::fromCustomerDAO)
                .collect(Collectors.toSet());
    }
}
