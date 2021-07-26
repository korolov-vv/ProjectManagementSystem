package ua.goit.service.customers;

import ua.goit.dao.model.CompanyDAO;
import ua.goit.dao.model.CustomerDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.dto.CustomerDTO;
import ua.goit.service.companies.CompaniesConverter;

import java.util.Set;

public class CustomersConverter {
    public static CustomerDAO toCustomerDAO(CustomerDTO customerDTO){
        Set<CompanyDAO> companies = CompaniesConverter.toCompaniesDAOSet(customerDTO.getCompanies());
        return new CustomerDAO(customerDTO.getCustomerId(), customerDTO.getCustomerName(), companies);
    }

    public static CustomerDTO fromCustomerDAO(CustomerDAO customerDAO) {
        Set<CompanyDTO> companies = CompaniesConverter.fromCompaniesDAOSet(customerDAO.getCompanies());
        return new CustomerDTO(customerDAO.getCustomerId(), customerDAO.getCustomerName(), companies);
    }
}
