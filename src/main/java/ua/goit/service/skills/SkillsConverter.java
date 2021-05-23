package ua.goit.service.skills;

import ua.goit.dao.model.SkillsDAO;
import ua.goit.dto.SkillsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillsConverter {
    public static SkillsDAO toSkill(SkillsDTO skillsDTO) {
        return new SkillsDAO(skillsDTO.getRecordId(), skillsDTO.getDeveloperId(),
                skillsDTO.getSatck(), skillsDTO.getLevel());
    }

    public static SkillsDTO fromSkill(SkillsDAO skillsDAO) {
        return new SkillsDTO(skillsDAO.getRecordId(), skillsDAO.getDeveloperId(),
                skillsDAO.getSatck(), skillsDAO.getLevel());
    }

    public static SkillsDAO toSkill(ResultSet resultSet) throws SQLException {
        SkillsDAO skillsDAO = new SkillsDAO();
        while (resultSet.next()) {
            skillsDAO.setRecordId(resultSet.getInt("record_id"));
            skillsDAO.setDeveloperId(resultSet.getInt("developer_id"));
            skillsDAO.setSatck(resultSet.getString("stack"));
            skillsDAO.setLevel(resultSet.getString("level"));
        }
        return skillsDAO;
    }
}
