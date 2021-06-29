package ua.goit.service.customers;

import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dao.model.CustomersAndCompaniesDAO;
import ua.goit.dto.CustomersAndCompaniesDTO;

public class CustomersAndCompaniesService {
    private CustomersAndCompaniesRepository repository;

    public CustomersAndCompaniesService(CustomersAndCompaniesRepository repository) {
        this.repository = repository;
    }

    public CustomersAndCompaniesDTO create(CustomersAndCompaniesDTO customersAndCompaniesDTO) {
        CustomersAndCompaniesDAO customersAndCompaniesDAO = CustomersAndCompaniesConverter.toCustomerAndCompanies(customersAndCompaniesDTO);
        repository.create(customersAndCompaniesDAO);
        CustomersAndCompaniesDAO savedCustomersAndCompaniesDAO = repository.findUniqueRecord(
                customersAndCompaniesDTO.getCompanyId(),
                customersAndCompaniesDTO.getCustomerId(),
                customersAndCompaniesDTO.getProjectId()
        );
        return CustomersAndCompaniesConverter.fromCustomerAndCompanies(savedCustomersAndCompaniesDAO);
    }

    public CustomersAndCompaniesDTO update(CustomersAndCompaniesDTO customersAndCompaniesDTO) {
        CustomersAndCompaniesDAO customersAndCompaniesDAO = CustomersAndCompaniesConverter.toCustomerAndCompanies(customersAndCompaniesDTO);
        repository.update(customersAndCompaniesDAO);
        CustomersAndCompaniesDAO updatedCustomersAndCompaniesDAO = repository.findUniqueRecord(
                customersAndCompaniesDTO.getCompanyId(),
                customersAndCompaniesDTO.getCustomerId(),
                customersAndCompaniesDTO.getProjectId()
        );
        return CustomersAndCompaniesConverter.fromCustomerAndCompanies(updatedCustomersAndCompaniesDAO);
    }

    public void deleteUniqueRecord(long companyId, long customerId, long projectId) {
        repository.deleteUniqueRecord(companyId, customerId, projectId);
    }

    public void deleteForCompany(long companyId) {
        repository.deleteForCompany(companyId);
    }

    public void deleteForCustomer(long customerId) {
        repository.deleteForCustomer(customerId);
    }

    public void deleteForProject(long projectId) {
        repository.deleteForProject(projectId);
    }
}
