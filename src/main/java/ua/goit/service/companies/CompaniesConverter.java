package ua.goit.service.companies;

import ua.goit.dao.model.CompanyDAO;
import ua.goit.dao.model.CustomerDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.dto.CustomerDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.customers.CustomersConverter;
import ua.goit.service.projects.ProjectsConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompaniesConverter {
    public static CompanyDAO toCompaniesDAO(CompanyDTO companyDTO) {
        Set<ProjectDAO> projects = toProjectsDAO(companyDTO.getProjects());
        Set<CustomerDAO> customers = toCustomersDAO(companyDTO.getCustomers());
        return new CompanyDAO(companyDTO.getCompanyId(), companyDTO.getCompanyName(),
                companyDTO.getNumberOfDevelopers(), projects, customers);
    }

    public static CompanyDTO fromCompaniesDAO(CompanyDAO companyDAO) {
        Set<ProjectDTO> projects = fromProjectsDAO(companyDAO.getProjects());
        Set<CustomerDTO> customers = fromCustomersDAO(companyDAO.getCustomers());
        return new CompanyDTO(companyDAO.getCompanyId(), companyDAO.getCompanyName(),
                companyDAO.getNumberOfDevelopers(), projects, customers);
    }

    public static List<CompanyDTO> fromCompaniesDAOList(List<CompanyDAO> companyDAOList) {
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        companyDAOList
                .forEach((company) -> {
                    companyDTOList.add(CompaniesConverter.fromCompaniesDAO(company));
                });
        return companyDTOList;
    }

    private static Set<ProjectDAO> toProjectsDAO(Set<ProjectDTO> projectDTOSet) {
        if(projectDTOSet == null) {
            return new HashSet<>();
        }else return projectDTOSet.stream()
                .map(ProjectsConverter::toProjectsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<CustomerDAO> toCustomersDAO(Set<CustomerDTO> customerDTOSet) {
        if(customerDTOSet == null) {
            return new HashSet<>();
        }else return customerDTOSet.stream()
                .map(CustomersConverter::toCustomerDAO)
                .collect(Collectors.toSet());
    }

    private static Set<ProjectDTO> fromProjectsDAO(Set<ProjectDAO> projectDAOSet) {
        if(projectDAOSet == null) {
            return new HashSet<>();
        }else return projectDAOSet.stream()
                .map(ProjectsConverter::fromProjectsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<CustomerDTO> fromCustomersDAO(Set<CustomerDAO> customerDAOSet) {
        if(customerDAOSet == null) {
            return new HashSet<>();
        }else return customerDAOSet.stream()
                .map(CustomersConverter::fromCustomerDAO)
                .collect(Collectors.toSet());
    }
}
