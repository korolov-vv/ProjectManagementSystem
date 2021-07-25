package ua.goit.dao.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class CustomerDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "customer_name")
    private String customerName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "companies_customers",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_id") }
    )
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<CompanyDAO> companies = new HashSet<>();

    public CustomerDAO() {
    }

    public CustomerDAO(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.companies = new HashSet<>();
    }

    public CustomerDAO(int customerId, String customerName, Set<CompanyDAO> companies) {
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

    public Set<CompanyDAO> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyDAO> companies) {
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
