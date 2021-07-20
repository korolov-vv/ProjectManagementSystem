package ua.goit.dto;

import java.util.List;

public class CompaniesDTO {
    private int companyId;
    private String companyName;
    private int numberOfDevelopers;
    private List<Long> projectIds;
    private List<Long> customerIds;

    public CompaniesDTO() {
    }

    public CompaniesDTO(int companyId, String companyName, int numberOfDevelopers) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public CompaniesDTO(int companyId, String companyName, int numberOfDevelopers, List<Long> projectIds, List<Long> customerIds) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.numberOfDevelopers = numberOfDevelopers;
        this.projectIds = projectIds;
        this.customerIds = customerIds;
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

    public List<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    @Override
    public String toString() {
        return "CompaniesDTO{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", numberOfDevelopers=" + numberOfDevelopers +
                ", projectIds=" + projectIds +
                ", customerIds=" + customerIds +
                '}';
    }
}
