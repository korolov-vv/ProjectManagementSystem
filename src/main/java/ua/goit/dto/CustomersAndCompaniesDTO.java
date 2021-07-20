package ua.goit.dto;

public class CustomersAndCompaniesDTO {
    private int companyId;
    private int customerId;
    private int projectId;

    public CustomersAndCompaniesDTO() {
    }

    public CustomersAndCompaniesDTO(int companyId, int customerId, int projectId) {
        this.companyId = companyId;
        this.customerId = customerId;
        this.projectId = projectId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "CustomersAndCompaniesDTO{" +
                "companyId=" + companyId +
                ", customerId=" + customerId +
                ", projectId=" + projectId +
                '}';
    }
}
