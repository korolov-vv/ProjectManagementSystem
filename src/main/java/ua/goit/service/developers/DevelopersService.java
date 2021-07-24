package ua.goit.service.developers;

import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dto.DevelopersDTO;

import java.util.List;

public class DevelopersService {

    private SingleEntityRepository<DevelopersDAO> repository;

    public DevelopersService(SingleEntityRepository<DevelopersDAO> repository) {
        this.repository = repository;
    }


    public DevelopersDTO create(DevelopersDTO developersDTO) {
        DevelopersDAO developersDAO = DevelopersConverter.toDevelopersDAO(developersDTO);
        repository.create(developersDAO);
        DevelopersDAO savedDevelopersDAO = repository.findByUniqueValue(developersDTO.getDeveloperEmail());
        return DevelopersConverter.fromDevelopersDAO(savedDevelopersDAO);
    }

    public DevelopersDTO update(DevelopersDTO developersDTO) {
        DevelopersDAO developersDAO = DevelopersConverter.toDevelopersDAO(developersDTO);
        repository.update(developersDAO);
        DevelopersDAO updatedDevelopersDAO = repository.findByUniqueValue(developersDTO.getDeveloperEmail());
        return DevelopersConverter.fromDevelopersDAO(updatedDevelopersDAO);
    }

    public void delete(String email) {
        repository.delete(email);
    }

    public DevelopersDTO findByEmail(String email) {
        return DevelopersConverter.fromDevelopersDAO(repository.findByUniqueValue(email));
    }

    public List<DevelopersDTO> findAll(){
        return DevelopersConverter.fromDevelopersDAOList(repository.findAll());
    }
}
