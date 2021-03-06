package ua.goit.dao.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "companies")
public class CompanyDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", nullable = false)
    private int companyId;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "number_of_developers")
    private int numberOfDevelopers;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "companies_projects",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    Set<ProjectDAO> projects = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "companies_customers",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "customer_id") }
    )
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    Set<CustomerDAO> customers = new HashSet<>();

    public CompanyDAO() {
    }

    public CompanyDAO(int companyId, String companyName, int numberOfDevelopers) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.numberOfDevelopers = numberOfDevelopers;
        this.projects = new HashSet<>();
        this.customers = new HashSet<>();
    }

    public CompanyDAO(int companyId, String companyName, int numberOfDevelopers, Set<ProjectDAO> projects,
                      Set<CustomerDAO> customers) {
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

    public Set<ProjectDAO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDAO> projects) {
        this.projects = projects;
    }

    public Set<CustomerDAO> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerDAO> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "CompaniesDAO{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", numberOfDevelopers=" + numberOfDevelopers +
                ", projects=" + projects +
                ", customers=" + customers +
                '}';
    }
}
