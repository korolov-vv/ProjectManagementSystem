package ua.goit.service.skills;

import ua.goit.dao.model.SkillDAO;
import ua.goit.dto.SkillDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SkillsConverter {
    public static SkillDAO toSkillsDAO(SkillDTO skillDTO) {
        return new SkillDAO(skillDTO.getRecordId(), skillDTO.getStack(),
                skillDTO.getLevel(), skillDTO.getDeveloperId());
    }

    public static SkillDTO fromSkillsDAO(SkillDAO skillDAO) {
        return new SkillDTO(skillDAO.getRecordId(), skillDAO.getStack(),
                skillDAO.getLevel(), skillDAO.getDeveloperId());
    }

    public static Set<SkillDTO> fromSkillsDAOSet(Set<SkillDAO> skillDAOSet) {
        if(skillDAOSet == null) {
            return new HashSet<>();
        }else return skillDAOSet.stream()
                .map(SkillsConverter::fromSkillsDAO)
                .collect(Collectors.toSet());
    }

    public static Set<SkillDAO> toSkillsDAOSet(Set<SkillDTO> skillDTOSet) {
        if(skillDTOSet == null) {
            return new HashSet<>();
        }else return skillDTOSet.stream()
                .map(SkillsConverter::toSkillsDAO)
                .collect(Collectors.toSet());
    }
}
