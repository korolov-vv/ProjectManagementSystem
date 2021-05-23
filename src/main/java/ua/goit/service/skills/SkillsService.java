package ua.goit.service.skills;

import ua.goit.dao.Repository;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.dto.SkillsDTO;

public class SkillsService {
    private Repository<SkillsDAO> repository;

    public SkillsService(Repository<SkillsDAO> repository) {
        this.repository = repository;
    }

    public SkillsDTO create(SkillsDTO skillsDTO) {
        SkillsDAO skillsDAO = SkillsConverter.toSkill(skillsDTO);
        repository.create(skillsDAO);
        SkillsDAO savedSkillsDAO = repository.findById(skillsDAO.getDeveloperId());
        return SkillsConverter.fromSkill(savedSkillsDAO);
    }

    public SkillsDTO update(SkillsDTO skillsDTO) {
        SkillsDAO skillsDAO = SkillsConverter.toSkill(skillsDTO);
        repository.update(skillsDAO);
        SkillsDAO supdatedSkillsDAO = repository.findById(skillsDTO.getDeveloperId());
        return SkillsConverter.fromSkill(supdatedSkillsDAO);
    }

    public void delete(int id) {
        repository.delete(id);
    }
}
