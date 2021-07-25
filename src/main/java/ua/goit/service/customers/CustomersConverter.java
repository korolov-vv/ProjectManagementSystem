package ua.goit.service.customers;

import ua.goit.dao.model.CompanyDAO;
import ua.goit.dao.model.CustomerDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.dto.CustomerDTO;
import ua.goit.service.companies.CompaniesConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomersConverter {
    public static CustomerDAO toCustomerDAO(CustomerDTO customerDTO){
        Set<CompanyDAO> companies = toCompaniesDAO(customerDTO.getCompanies());
        return new CustomerDAO(customerDTO.getCustomerId(), customerDTO.getCustomerName(), companies);
    }

    public static CustomerDTO fromCustomerDAO(CustomerDAO customerDAO) {
        Set<CompanyDTO> companies = fromCompaniesDAO(customerDAO.getCompanies());
        return new CustomerDTO(customerDAO.getCustomerId(), customerDAO.getCustomerName(), companies);
    }

    private static Set<CompanyDAO> toCompaniesDAO(Set<CompanyDTO> companyDTOSet) {
        if(companyDTOSet == null) {
            return new HashSet<>();
        }else return companyDTOSet.stream()
                .map(CompaniesConverter::toCompaniesDAO)
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
