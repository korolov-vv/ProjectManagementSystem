package ua.goit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillsDTO {
    int recordId;
    int developerId;
    String satck;
    String level;

    public SkillsDTO() {
    }

    public SkillsDTO(int recordId, int developerId, String satck, String level) {
        this.recordId = recordId;
        this.developerId = developerId;
        this.satck = satck;
        this.level = level;
    }

    @Override
    public String toString() {
        return "SkillsDTO{" +
                "recordId=" + recordId +
                ", developerId=" + developerId +
                ", satck='" + satck + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
