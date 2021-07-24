package ua.goit.service.customers;

import ua.goit.dao.model.CustomersDAO;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dto.CustomersDTO;

public class CustomersService {
    private SingleEntityRepository<CustomersDAO> repository;

    public CustomersService(SingleEntityRepository<CustomersDAO> repository) {
        this.repository = repository;
    }

    public CustomersDTO create(CustomersDTO customersDTO) {
        CustomersDAO customersDAO = CustomersConverter.toCustomerDAO(customersDTO);
        repository.create(customersDAO);
        CustomersDAO savedCustomersDAO = repository.findById(customersDAO.getCustomerId());
        return CustomersConverter.fromCustomerDAO(savedCustomersDAO);
    }

    public CustomersDTO update(CustomersDTO customersDTO) {
        CustomersDAO customersDAO = CustomersConverter.toCustomerDAO(customersDTO);
        repository.update(customersDAO);
        CustomersDAO updatedCustomersDAO = repository.findByUniqueValue(customersDTO.getCustomerName());
        return CustomersConverter.fromCustomerDAO(updatedCustomersDAO);
    }
}
