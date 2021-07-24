package ua.goit.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ProjectsDTO {
    private int projectId;
    private String projectName;
    private String stage;
    private int timePeriod;
    private int coast;
    private int numberOfDevelopers;
    private LocalDate dateOfBeginning;
    private Set<DevelopersDTO> developers;
    private Set<CompaniesDTO> companies;


    public ProjectsDTO() {
    }

    public ProjectsDTO(int projectId, String projectName, String stage, int timePeriod, int coast,
                       int numberOfDevelopers, LocalDate dateOfBeginning) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.stage = stage;
        this.timePeriod = timePeriod;
        this.coast = coast;
        this.numberOfDevelopers = numberOfDevelopers;
        this.dateOfBeginning = dateOfBeginning;
        this.developers = new HashSet<>();
        this.companies = new HashSet<>();
    }

    public ProjectsDTO(int projectId, String projectName, String stage, int timePeriod, int coast,
                       int numberOfDevelopers, LocalDate dateOfBeginning, Set<DevelopersDTO> developers,
                       Set<CompaniesDTO> companies) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.stage = stage;
        this.timePeriod = timePeriod;
        this.coast = coast;
        this.numberOfDevelopers = numberOfDevelopers;
        this.dateOfBeginning = dateOfBeginning;
        this.developers = developers;
        this.companies = companies;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
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

    public int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    public void setNumberOfDevelopers(int numberOfDevelopers) {
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public LocalDate getDateOfBeginning() {
        return dateOfBeginning;
    }

    public void setDateOfBeginning(LocalDate dateOfBeginning) {
        this.dateOfBeginning = dateOfBeginning;
    }

    public Set<DevelopersDTO> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DevelopersDTO> developers) {
        this.developers = developers;
    }

    public Set<CompaniesDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompaniesDTO> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "ProjectsDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", stage='" + stage + '\'' +
                ", timePeriod=" + timePeriod +
                ", coast=" + coast +
                ", numberOfDevelopers=" + numberOfDevelopers +
                ", dateOfBeginning=" + dateOfBeginning +
                ", developers=" + developers +
                ", companies=" + companies +
                '}';
    }
}
