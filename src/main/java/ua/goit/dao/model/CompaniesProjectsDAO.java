package ua.goit.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "companies_projects")
public class CompaniesProjectsDAO implements Serializable {

    @Id
    @Column(name = "company_id")
    int companyId;
    @Id
    @Column(name = "project_id")
    int projectId;

    public CompaniesProjectsDAO() {
    }

    public CompaniesProjectsDAO(int companyId, int projectId) {
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
