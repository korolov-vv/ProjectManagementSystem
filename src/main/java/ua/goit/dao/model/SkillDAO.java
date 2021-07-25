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
    @Enumerated(EnumType.STRING)
    private Stack stack;
    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Levels level;
    @Column(name = "developer_email")
    private String developerEmail;
    @ManyToOne(targetEntity = DeveloperDAO.class, fetch = FetchType.EAGER)
    @JoinColumn(name="developer_email", insertable=false, updatable=false, nullable = false)
    private DeveloperDAO developerDAO;

    public SkillDAO() {
    }

    public SkillDAO(int recordId, Stack stack, Levels level, String developerEmail) {
        this.recordId = recordId;
        this.stack = stack;
        this.level = level;
        this.developerEmail = developerEmail;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
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

    public DeveloperDAO getDevelopersDAO() {
        return developerDAO;
    }

    public void setDevelopersDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    @Override
    public String toString() {
        return "SkillsDAO{" +
                "recordId=" + recordId +
                ", stack=" + stack +
                ", level=" + level +
                ", developerEmail='" + developerEmail + '\'' +
                '}';
    }
}
