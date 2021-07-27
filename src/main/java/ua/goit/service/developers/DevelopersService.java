package ua.goit.service.developers;

import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dto.DeveloperDTO;

import java.util.List;

public class DevelopersService {

    private final SingleEntityRepository<DeveloperDAO> repository;

    public DevelopersService(SingleEntityRepository<DeveloperDAO> repository) {
        this.repository = repository;
    }


    public DeveloperDTO create(DeveloperDTO developerDTO) {
        DeveloperDAO developerDAO = DevelopersConverter.toDevelopersDAO(developerDTO);
        repository.create(developerDAO);
        DeveloperDAO savedDeveloperDAO = repository.findByUniqueParameter("developerEmail",
                developerDTO.getDeveloperEmail()).orElseThrow();
        return DevelopersConverter.fromDevelopersDAO(savedDeveloperDAO);
    }

    public DeveloperDTO update(DeveloperDTO developerDTO) {
        DeveloperDAO developerDAO = DevelopersConverter.toDevelopersDAO(developerDTO);
        repository.update(developerDAO);
        DeveloperDAO updatedDeveloperDAO = repository.findByUniqueParameter("developerEmail",
                developerDTO.getDeveloperEmail()).orElseThrow();
        return DevelopersConverter.fromDevelopersDAO(updatedDeveloperDAO);
    }

    public void delete(String email) {
        repository.deleteByParameter("developerEmail", email);
    }

    public DeveloperDTO findById(int id) {
        return DevelopersConverter.fromDevelopersDAO(repository.findById(id).orElseThrow());
    }

    public DeveloperDTO findByEmail(String email) {
        return DevelopersConverter.fromDevelopersDAO(repository.findByUniqueParameter("developerEmail", email).orElseThrow());
    }

    public List<DeveloperDTO> findAll(){
        return DevelopersConverter.fromDevelopersDAOList(repository.findAll());
    }
}
