package ua.goit.dto;

import java.util.HashSet;
import java.util.Set;

public class CustomerDTO {
    private int customerId;
    private String customerName;
    private Set<CompanyDTO> companies;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.companies = new HashSet<>();
    }

    public CustomerDTO(int customerId, String customerName, Set<CompanyDTO> companies) {
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

    public Set<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyDTO> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "CustomersDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", companies=" + companies +
                '}';
    }
}
