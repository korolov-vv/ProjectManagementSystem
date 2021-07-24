package ua.goit.dao.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class CustomersDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "customer_name")
    private String customerName;
    @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<CompaniesDAO> companies = new HashSet<>();

    public CustomersDAO() {
    }

    public CustomersDAO(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.companies = new HashSet<>();
    }

    public CustomersDAO(int customerId, String customerName, Set<CompaniesDAO> companies) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.companies = companies;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<CompaniesDAO> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompaniesDAO> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
