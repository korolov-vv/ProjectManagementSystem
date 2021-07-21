package ua.goit.dto;

public class CompaniesCustomersDTO {
    int companyId;
    int customerId;

    public CompaniesCustomersDTO() {
    }

    public CompaniesCustomersDTO(int companyId, int customerId) {
        this.companyId = companyId;
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "CompaniesCustomersDTO{" +
                "companyId=" + companyId +
                ", customerId=" + customerId +
                '}';
    }
}
