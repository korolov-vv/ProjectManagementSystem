package ua.goit.dto;

public class SkillDTO {
    private int recordId;
    private String stack;
    private String level;
    private int developerId;

    public SkillDTO() {
    }

    public SkillDTO(int recordId, String stack, String level, int developerId) {
        this.recordId = recordId;
        this.stack = stack;
        this.level = level;
        this.developerId = developerId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "recordId=" + recordId +
                ", stack=" + stack +
                ", level=" + level +
                ", developerId=" + developerId +
                '}';
    }
}
