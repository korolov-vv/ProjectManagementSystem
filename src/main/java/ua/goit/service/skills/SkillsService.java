package ua.goit.service.skills;

import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.dto.SkillsDTO;

public class SkillsService {
    private SingleEntityRepository<SkillsDAO> repository;

    public SkillsService(SingleEntityRepository<SkillsDAO> repository) {
        this.repository = repository;
    }

    public SkillsDTO create(SkillsDTO skillsDTO) {
        SkillsDAO skillsDAO = SkillsConverter.toSkillsDAO(skillsDTO);
        repository.create(skillsDAO);
        SkillsDAO savedSkillsDAO = repository.findByUniqueValue(skillsDAO.getDeveloperEmail());
        return SkillsConverter.fromSkillsDAO(savedSkillsDAO);
    }

    public SkillsDTO update(SkillsDTO skillsDTO) {
        SkillsDAO skillsDAO = SkillsConverter.toSkillsDAO(skillsDTO);
        repository.update(skillsDAO);
        SkillsDAO updatedSkillsDAO = repository.findByUniqueValue(skillsDTO.getDeveloperEmail());
        return SkillsConverter.fromSkillsDAO(updatedSkillsDAO);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public SkillsDTO findByDeveloper(String email) {
        return SkillsConverter.fromSkillsDAO(repository.findByUniqueValue(email));
    }
}
