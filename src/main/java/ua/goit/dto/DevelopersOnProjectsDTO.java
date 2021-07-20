package ua.goit.dto;

public class DevelopersOnProjectsDTO {
    private int developerId;
    private int projectId;

    public DevelopersOnProjectsDTO() {
    }

    public DevelopersOnProjectsDTO(int developerId, int projectId) {
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
        return "DevelopersOnProjectsDTO{" +
                "developerId=" + developerId +
                ", projectId=" + projectId +
                '}';
    }
}
