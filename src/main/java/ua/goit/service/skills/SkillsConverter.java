package ua.goit.service.skills;

import ua.goit.dao.model.SkillDAO;
import ua.goit.dto.SkillDTO;

public class SkillsConverter {
    public static SkillDAO toSkillsDAO(SkillDTO skillDTO) {
        return new SkillDAO(skillDTO.getRecordId(), skillDTO.getStack(),
                skillDTO.getLevel(), skillDTO.getDeveloperEmail());
    }

    public static SkillDTO fromSkillsDAO(SkillDAO skillDAO) {
        return new SkillDTO(skillDAO.getRecordId(), skillDAO.getStack(),
                skillDAO.getLevel(), skillDAO.getDeveloperEmail());
    }
}
