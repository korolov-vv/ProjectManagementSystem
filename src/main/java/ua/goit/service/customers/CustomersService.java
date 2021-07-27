package ua.goit.service.customers;

import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CustomerDAO;
import ua.goit.dto.CustomerDTO;

public class CustomersService {
    private final SingleEntityRepository<CustomerDAO> repository;

    public CustomersService(SingleEntityRepository<CustomerDAO> repository) {
        this.repository = repository;
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        CustomerDAO customerDAO = CustomersConverter.toCustomerDAO(customerDTO);
        repository.create(customerDAO);
        CustomerDAO savedCustomerDAO = repository.findById(customerDAO.getCustomerId()).orElseThrow();
        return CustomersConverter.fromCustomerDAO(savedCustomerDAO);
    }

    public CustomerDTO update(CustomerDTO customerDTO) {
        CustomerDAO customerDAO = CustomersConverter.toCustomerDAO(customerDTO);
        repository.update(customerDAO);
        CustomerDAO updatedCustomerDAO = repository.findByUniqueParameter("customerName",
                customerDTO.getCustomerName()).orElseThrow();
        return CustomersConverter.fromCustomerDAO(updatedCustomerDAO);
    }
}
