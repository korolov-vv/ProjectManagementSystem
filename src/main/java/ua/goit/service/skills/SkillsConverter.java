package ua.goit.service.skills;

import ua.goit.dao.model.SkillsDAO;
import ua.goit.dto.SkillsDTO;

public class SkillsConverter {
    public static SkillsDAO toSkillsDAO(SkillsDTO skillsDTO) {
        return new SkillsDAO(skillsDTO.getRecordId(), skillsDTO.getStack(),
                skillsDTO.getLevel(), skillsDTO.getDeveloperEmail());
    }

    public static SkillsDTO fromSkillsDAO(SkillsDAO skillsDAO) {
        return new SkillsDTO(skillsDAO.getRecordId(), skillsDAO.getStack(),
                skillsDAO.getLevel(), skillsDAO.getDeveloperEmail());
    }
}
