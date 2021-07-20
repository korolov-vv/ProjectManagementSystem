package ua.goit.dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "developers_on_projects")
public class DevelopersOnProjectsDAO {
    @Id
    private int developerId;
    @Id
    private int projectId;

    public DevelopersOnProjectsDAO() {
    }

    public DevelopersOnProjectsDAO(int developerId, int projectId) {
        this.developerId = developerId;
        this.projectId = projectId;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "DevelopersOnProjectsDAO{" +
                "developerId=" + developerId +
                ", projectId=" + projectId +
                '}';
    }
}
