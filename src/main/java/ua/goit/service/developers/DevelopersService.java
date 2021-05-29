package ua.goit.service.developers;

import ua.goit.dao.Repository;
import ua.goit.dto.DevelopersDTO;
import ua.goit.dao.model.DevelopersDAO;

public class DevelopersService {

    private Repository<DevelopersDAO> repository;

    public DevelopersService(Repository<DevelopersDAO> repository) {
        this.repository = repository;
    }


    public DevelopersDTO create(DevelopersDTO developersDTO) {
        DevelopersDAO developersDAO = DevelopersConverter.toDeveloper(developersDTO);
        repository.create(developersDAO);
        DevelopersDAO savedDevelopersDAO = repository.findByEmail(developersDTO.getDeveloperEmail());
        return DevelopersConverter.fromDeveloper(savedDevelopersDAO);
    }

    public DevelopersDTO update(DevelopersDTO developersDTO) {
        DevelopersDAO developersDAO = DevelopersConverter.toDeveloper(developersDTO);
        repository.update(developersDAO);
        DevelopersDAO updatedDevelopersDAO = repository.findByEmail(developersDTO.getDeveloperEmail());
        return DevelopersConverter.fromDeveloper(updatedDevelopersDAO);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
