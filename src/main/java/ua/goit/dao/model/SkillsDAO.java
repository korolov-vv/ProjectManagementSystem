package ua.goit.dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class SkillsDAO {
    @Id
    private int recordId;
    private Stack satck;
    private Levels level;
    private String developerEmail;

    public SkillsDAO() {
    }

    public SkillsDAO(int recordId, Stack satck, Levels level, String developerEmail) {
        this.recordId = recordId;
        this.satck = satck;
        this.level = level;
        this.developerEmail = developerEmail;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
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
                ", stack=" + satck +
                ", level=" + level +
                ", developer_email='" + developerEmail + '\'' +
                '}';
    }
}
