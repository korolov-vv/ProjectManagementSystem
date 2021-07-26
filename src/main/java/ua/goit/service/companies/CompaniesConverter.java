package ua.goit.service.companies;

import ua.goit.dao.model.CompanyDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.service.customers.CustomersConverter;
import ua.goit.service.projects.ProjectsConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompaniesConverter {
    public static CompanyDAO toCompaniesDAO(CompanyDTO companyDTO) {
        CompanyDAO companyDAO = getCompanyDAO(companyDTO);
        companyDAO.setProjects(ProjectsConverter.toProjectsDAOSet(companyDTO.getProjects()));
        companyDAO.setCustomers(CustomersConverter.toCustomersDAOSet(companyDTO.getCustomers()));
        return companyDAO;
    }

    public static CompanyDTO fromCompaniesDAO(CompanyDAO companyDAO) {
        CompanyDTO companyDTO = getCompanyDTO(companyDAO);
        companyDTO.setProjects(ProjectsConverter.fromProjectsDAOSet(companyDAO.getProjects()));
        companyDTO.setCustomers(CustomersConverter.fromCustomersDAOSet(companyDAO.getCustomers()));
        return companyDTO;
    }

    private static CompanyDTO getCompanyDTO(CompanyDAO companyDAO) {
        return new CompanyDTO(companyDAO.getCompanyId(), companyDAO.getCompanyName(),
                companyDAO.getNumberOfDevelopers());
    }

    public static List<CompanyDTO> fromCompaniesDAOList(List<CompanyDAO> companyDAOList) {
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        companyDAOList
                .forEach((company) -> {
                    companyDTOList.add(CompaniesConverter.fromCompaniesDAO(company));
                });
        return companyDTOList;
    }

    public static Set<CompanyDAO> toCompaniesDAOSet(Set<CompanyDTO> companyDTOSet) {
        if(companyDTOSet == null) {
            return new HashSet<>();
        }else return companyDTOSet.stream()
                .map(CompaniesConverter::getCompanyDAO)
                .collect(Collectors.toSet());
    }

    public static Set<CompanyDTO> fromCompaniesDAOSet(Set<CompanyDAO> companyDAOSet) {
        if(companyDAOSet == null) {
            return new HashSet<>();
        }else return companyDAOSet.stream()
                .map(CompaniesConverter::fromCompaniesDAOForSet)
                .collect(Collectors.toSet());
    }

    private static CompanyDAO getCompanyDAO(CompanyDTO companyDTO) {
        return new CompanyDAO(companyDTO.getCompanyId(), companyDTO.getCompanyName(),
                companyDTO.getNumberOfDevelopers());
    }

    private static CompanyDTO fromCompaniesDAOForSet(CompanyDAO companyDAO) {
        return getCompanyDTO(companyDAO);
    }
}