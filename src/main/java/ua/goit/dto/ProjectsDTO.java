package ua.goit.dto;

public class ProjectsDTO {
    private long projectId;
    private long companyId;
    private long customerId;
    private String projectName;
    private String stage;
    private int timePeriod;
    private int coast;

    public ProjectsDTO() {
    }

    public ProjectsDTO(long projectId, long companyId, long customerId, String projectName, String stage,
                       int timePeriod, int coast) {
        this.projectId = projectId;
        this.companyId = companyId;
        this.customerId = customerId;
        this.projectName = projectName;
        this.stage = stage;
        this.timePeriod = timePeriod;
        this.coast = coast;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "ProjectsDAO{" +
                "projectId=" + projectId +
                ", companyId=" + companyId +
                ", customerId=" + customerId +
                ", projectName='" + projectName + '\'' +
                ", stage='" + stage + '\'' +
                ", timePeriod=" + timePeriod +
                ", coast=" + coast +
                '}';
    }
}
