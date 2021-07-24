package ua.goit.service.customers;

import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dao.model.CustomersDAO;
import ua.goit.dto.CompaniesDTO;
import ua.goit.dto.CustomersDTO;
import ua.goit.service.companies.CompaniesConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomersConverter {
    public static CustomersDAO toCustomerDAO(CustomersDTO customersDTO){
        Set<CompaniesDAO> companies = toCompaniesDAO(customersDTO.getCompanies());
        return new CustomersDAO(customersDTO.getCustomerId(), customersDTO.getCustomerName(), companies);
    }

    public static CustomersDTO fromCustomerDAO(CustomersDAO customersDAO) {
        Set<CompaniesDTO> companies = fromCompaniesDAO(customersDAO.getCompanies());
        return new CustomersDTO(customersDAO.getCustomerId(), customersDAO.getCustomerName(), companies);
    }

    private static Set<CompaniesDAO> toCompaniesDAO(Set<CompaniesDTO> companiesDTOSet) {
        if(companiesDTOSet == null) {
            return new HashSet<>();
        }else return companiesDTOSet.stream()
                .map(CompaniesConverter::toCompaniesDAO)
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
