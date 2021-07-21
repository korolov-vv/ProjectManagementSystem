package ua.goit.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "companies")
public class CompaniesDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyId;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "number_of_developers")
    private int numberOfDevelopers;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "companies_projects",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    Set<ProjectsDAO> projects = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "companies_customers",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "customer_id") }
    )
    Set<CustomersDAO> customers = new HashSet<>();

    public CompaniesDAO() {
    }

    public CompaniesDAO(int companyId, String companyName, int numberOfDevelopers) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.numberOfDevelopers = numberOfDevelopers;
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

    public Set<ProjectsDAO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDAO> projects) {
        this.projects = projects;
    }

    public Set<CustomersDAO> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomersDAO> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", numberOfDevelopers=" + numberOfDevelopers +
                '}';
    }
}
