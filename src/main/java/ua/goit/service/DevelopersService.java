package ua.goit.service;

import ua.goit.dto.DevelopersDTO;
import ua.goit.dao.Repository;
import ua.goit.dao.model.DevelopersDAO;

public class DevelopersService {
    private Repository<DevelopersDAO> repository;

    public DevelopersService(Repository<DevelopersDAO> repository) {
        this.repository = repository;
    }

    public DevelopersDTO create(DevelopersDTO developersDTO) {
        DevelopersDAO developersDAO = DevelopersConverter.toDeveloper(developersDTO);
        repository.create(developersDAO);
        DevelopersDAO savedLocationDAO = repository.findById(developersDAO.getDeveloperId());
        return DevelopersConverter.fromDeveloper(savedLocationDAO);
    }

    public DevelopersDTO update(DevelopersDTO dto) {
        DevelopersDAO developersDAO = DevelopersConverter.toDeveloper(dto);
        repository.update(developersDAO);
        DevelopersDAO updatedDevelopersDAO = repository.findById(dto.getDeveloperId());
        return DevelopersConverter.fromDeveloper(updatedDevelopersDAO);
    }

    public void delete(int id) {
        repository.delete(id);
    }
}
