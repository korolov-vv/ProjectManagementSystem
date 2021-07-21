package ua.goit.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "companies_customers")
public class CompaniesCustomersDAO implements Serializable {

    @Id
    @Column(name = "company_id")
    int companyId;
    @Id
    @Column(name = "customer_id")
    int customerId;

    public CompaniesCustomersDAO() {
    }

    public CompaniesCustomersDAO(int companyId, int customerId) {
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
        return "CompaniesCustomersDAO{" +
                "companyId=" + companyId +
                ", customerId=" + customerId +
                '}';
    }
}
