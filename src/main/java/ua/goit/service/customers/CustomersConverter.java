package ua.goit.service.customers;

import ua.goit.dao.model.CustomerDAO;
import ua.goit.dto.CustomerDTO;
import ua.goit.service.companies.CompaniesConverter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomersConverter {
    public static CustomerDAO toCustomerDAO(CustomerDTO customerDTO){
        CustomerDAO customerDAO = getCustomerDAO(customerDTO);
        customerDAO.setCompanies(CompaniesConverter.toCompaniesDAOSet(customerDTO.getCompanies()));
        return customerDAO;
    }

    public static CustomerDTO fromCustomerDAO(CustomerDAO customerDAO) {
        CustomerDTO customerDTO = getCustomerDTO(customerDAO);
        customerDTO.setCompanies(CompaniesConverter.fromCompaniesDAOSet(customerDAO.getCompanies()));
        return customerDTO;
    }

    public static Set<CustomerDAO> toCustomersDAOSet(Set<CustomerDTO> customerDTOSet) {
        if(customerDTOSet.size() == 0) {
            return new HashSet<>();
        }else return customerDTOSet.stream()
                .map(CustomersConverter::getCustomerDAO)
                .collect(Collectors.toSet());
    }

    public static Set<CustomerDTO> fromCustomersDAOSet(Set<CustomerDAO> customerDAOSet) {
        if(customerDAOSet.size() == 0) {
            return new HashSet<>();
        }else return customerDAOSet.stream()
                .map(CustomersConverter::getCustomerDTO)
                .collect(Collectors.toSet());
    }

    private static CustomerDAO getCustomerDAO(CustomerDTO customerDTO) {
        return new CustomerDAO(customerDTO.getCustomerId(), customerDTO.getCustomerName());
    }

    private static CustomerDTO getCustomerDTO(CustomerDAO customerDAO) {
        return new CustomerDTO(customerDAO.getCustomerId(), customerDAO.getCustomerName());
    }
}
