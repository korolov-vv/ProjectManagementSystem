package ua.goit.dto;

import java.util.HashSet;
import java.util.Set;

public class CompaniesDTO {
    private int companyId;
    private String companyName;
    private int numberOfDevelopers;
    private Set<ProjectsDTO> projects;
    private Set<CustomersDTO> customers;

    public CompaniesDTO() {
    }

    public CompaniesDTO(int companyId, String companyName, int numberOfDevelopers) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.numberOfDevelopers = numberOfDevelopers;
        this.projects = new HashSet<>();
        this.customers = new HashSet<>();
    }

    public CompaniesDTO(int companyId, String companyName, int numberOfDevelopers, Set<ProjectsDTO> projects, Set<CustomersDTO> customers) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.numberOfDevelopers = numberOfDevelopers;
        this.projects = projects;
        this.customers = customers;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    public void setNumberOfDevelopers(int numberOfDevelopers) {
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public Set<ProjectsDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDTO> projects) {
        this.projects = projects;
    }

    public Set<CustomersDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomersDTO> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "CompaniesDTO{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", numberOfDevelopers=" + numberOfDevelopers +
                ", projects=" + projects +
                ", customers=" + customers +
                '}';
    }
}
