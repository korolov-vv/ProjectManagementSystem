package ua.goit.dto;

import ua.goit.dao.model.Levels;
import ua.goit.dao.model.Stack;

public class SkillsDTO {
    private long recordId;
    private Stack satck;
    private Levels level;
    private String developerEmail;

    public SkillsDTO() {
    }

    public SkillsDTO(long recordId, Stack satck, Levels level, String developerEmail) {
        this.recordId = recordId;
        this.satck = satck;
        this.level = level;
        this.developerEmail = developerEmail;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public Stack getSatck() {
        return satck;
    }

    public void setSatck(Stack satck) {
        this.satck = satck;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public String getDeveloperEmail() {
        return developerEmail;
    }

    public void setDeveloperEmail(String developerEmail) {
        this.developerEmail = developerEmail;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "recordId=" + recordId +
                ", satck=" + satck +
                ", level=" + level +
                ", developer_id='" + developerEmail + '\'' +
                '}';
    }
}
