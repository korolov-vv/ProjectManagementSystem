package ua.goit.dao.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "skills")
public class SkillDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false)
    private int recordId;
    @Column(name = "stack")
    private String stack;
    @Column(name = "level")
    private String level;
    @Column(name = "developer_id")
    private int developerId;
    @ManyToOne(targetEntity = DeveloperDAO.class, fetch = FetchType.EAGER)
    @JoinColumn(name="developer_id", insertable=false, updatable=false, nullable = false)
    private DeveloperDAO developerDAO;

    public SkillDAO() {
    }

    public SkillDAO(int recordId, String stack, String level, int developerId) {
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

    public void setDeveloperId(int  developerId) {
        this.developerId = developerId;
    }

    public DeveloperDAO getDeveloperDAO() {
        return developerDAO;
    }

    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    public DeveloperDAO getDevelopersDAO() {
        return developerDAO;
    }

    public void setDevelopersDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    @Override
    public String toString() {
        return "SkillDAO{" +
                "recordId=" + recordId +
                ", stack=" + stack +
                ", level=" + level +
                ", developerId=" + developerId +
                ", developerDAO=" + developerDAO +
                '}';
    }
}
