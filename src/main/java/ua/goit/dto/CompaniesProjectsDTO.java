package ua.goit.dto;

public class CompaniesProjectsDTO {
    int companyId;
    int projectId;

    public CompaniesProjectsDTO() {
    }

    public CompaniesProjectsDTO(int companyId, int projectId) {
        this.companyId = companyId;
        this.projectId = projectId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCustomerId() {
        return projectId;
    }

    public void setCustomerId(int customerId) {
        this.projectId = customerId;
    }

    @Override
    public String toString() {
        return "CompaniesCustomersDTO{" +
                "companyId=" + companyId +
                ", customerId=" + projectId +
                '}';
    }
}
