package ua.goit.service.companies;

import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CompanyDAO;
import ua.goit.dto.CompanyDTO;

import java.util.List;

public class CompaniesService {
    private final SingleEntityRepository<CompanyDAO> repository;

    public CompaniesService(SingleEntityRepository<CompanyDAO> repository){
        this.repository = repository;
    }

    public CompanyDTO create(CompanyDTO companyDTO) {
        CompanyDAO companyDAO = CompaniesConverter.toCompaniesDAO(companyDTO);
        repository.create(companyDAO);
        CompanyDAO savedCompanyDAO = repository.findById(companyDAO.getCompanyId()).orElseThrow();
        return CompaniesConverter.fromCompaniesDAO(savedCompanyDAO);
    }

    public CompanyDTO update(CompanyDTO companyDTO) {
        CompanyDAO companyDAO = CompaniesConverter.toCompaniesDAO(companyDTO);
        repository.update(companyDAO);
        CompanyDAO updatedCompanyDAO = repository.findByUniqueParameter("companyName", companyDTO.getCompanyName()).orElseThrow();
        return CompaniesConverter.fromCompaniesDAO(updatedCompanyDAO);
    }

    public void deleteByParameter(String name) {
        repository.deleteByParameter("companyName", name);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public CompanyDTO findById(int id) {
        return CompaniesConverter.fromCompaniesDAO(repository.findById(id).orElseThrow());
    }

    public CompanyDTO findByName (String name) {
        return CompaniesConverter.fromCompaniesDAO(repository.findByUniqueParameter("companyName", name).orElseThrow());
    }

    public List<CompanyDTO> findAll() {
        return CompaniesConverter.fromCompaniesDAOList(repository.findAll());
    }
}
