package ua.goit.service.companies;

import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dto.CompaniesDTO;

public class CompaniesService {
    private SingleEntityRepository<CompaniesDAO> repository;

    public CompaniesService(SingleEntityRepository<CompaniesDAO> repository){
        this.repository = repository;
    }

    public CompaniesDTO create(CompaniesDTO companiesDTO) {
        CompaniesDAO companiesDAO = CompaniesConverter.toCompaniesDAO(companiesDTO);
        repository.create(companiesDAO);
        CompaniesDAO savedCompanyDAO = repository.findById(companiesDAO.getCompanyId());
        return CompaniesConverter.fromCompaniesDAO(savedCompanyDAO);
    }

    public CompaniesDTO update(CompaniesDTO companiesDTO) {
        CompaniesDAO companiesDAO = CompaniesConverter.toCompaniesDAO(companiesDTO);
        repository.update(companiesDAO);
        CompaniesDAO updatedCompaniessDAO = repository.findByUniqueValue(companiesDTO.getCompanyName());
        return CompaniesConverter.fromCompaniesDAO(updatedCompaniessDAO);
    }

    public void delete (String name) {
        repository.delete(name);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public CompaniesDTO findByName (String name) {
        return CompaniesConverter.fromCompaniesDAO(repository.findByUniqueValue(name));
    }

}
