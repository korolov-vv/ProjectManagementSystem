package ua.goit.service.skills;

import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.SkillDAO;
import ua.goit.dto.SkillDTO;

public class SkillsService {
    private final SingleEntityRepository<SkillDAO> repository;

    public SkillsService(SingleEntityRepository<SkillDAO> repository) {
        this.repository = repository;
    }

    public SkillDTO create(SkillDTO skillDTO) {
        SkillDAO skillDAO = SkillsConverter.toSkillsDAO(skillDTO);
        repository.create(skillDAO);
        SkillDAO savedSkillDAO = repository.findById(skillDAO.getDeveloperId()).orElseThrow();
        return SkillsConverter.fromSkillsDAO(savedSkillDAO);
    }

    public SkillDTO update(SkillDTO skillDTO) {
        SkillDAO skillDAO = SkillsConverter.toSkillsDAO(skillDTO);
        repository.update(skillDAO);
        SkillDAO updatedSkillDAO = repository.findById(skillDTO.getDeveloperId()).orElseThrow();
        return SkillsConverter.fromSkillsDAO(updatedSkillDAO);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public SkillDTO findByDeveloper(String developerEmail) {
        return SkillsConverter.fromSkillsDAO(repository.findByUniqueParameter("developerEmail", developerEmail).orElseThrow());
    }
}
