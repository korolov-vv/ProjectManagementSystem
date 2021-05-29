package ua.goit.dto;

import java.util.List;

public class ProjectsDTO {
    private long projectId;
    private String projectName;
    private String stage;
    private int timePeriod;
    private int coast;
    private List<DevelopersDTO> developers;

    public ProjectsDTO() {
    }

    public ProjectsDTO(long projectId, String projectName, String stage, int timePeriod, int coast) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.stage = stage;
        this.timePeriod = timePeriod;
        this.coast = coast;
    }

    public ProjectsDTO(long projectId, String projectName, String stage, int timePeriod, int coast, List<DevelopersDTO> developers) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.stage = stage;
        this.timePeriod = timePeriod;
        this.coast = coast;
        this.developers = developers;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    public List<DevelopersDTO> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<DevelopersDTO> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "ProjectsDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", stage='" + stage + '\'' +
                ", timePeriod=" + timePeriod +
                ", coast=" + coast +
                ", developers=" + developers +
                '}';
    }
}
