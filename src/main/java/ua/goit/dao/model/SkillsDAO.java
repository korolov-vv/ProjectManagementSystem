package ua.goit.dao.model;

public class SkillsDAO {
    private long recordId;
    private long developerId;
    private Stack satck;
    private Levels level;

    public SkillsDAO() {
    }

    public SkillsDAO(long recordId, long developerId, Stack satck, Levels level) {
        this.recordId = recordId;
        this.developerId = developerId;
        this.satck = satck;
        this.level = level;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
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

    @Override
    public String toString() {
        return "Skills{" +
                "recordId=" + recordId +
                ", developerId=" + developerId +
                ", satck='" + satck + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
