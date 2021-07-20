package ua.goit.dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers_and_companies")
public class CustomersAndCompaniesDAO {
    @Id
    private int companyId;
    @Id
    private int customerId;
    @Id
    private int projectId;

    public CustomersAndCompaniesDAO() {
    }

    public CustomersAndCompaniesDAO(int companyId, int customerId, int projectId) {
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
        return "CustomersAndCompaniesDAO{" +
                "companyId=" + companyId +
                ", customerId=" + customerId +
                ", projectId=" + projectId +
                '}';
    }
}
