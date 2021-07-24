package ua.goit.dto;

import java.util.HashSet;
import java.util.Set;

public class CustomersDTO {
    private int customerId;
    private String customerName;
    private Set<CompaniesDTO> companies;

    public CustomersDTO() {
    }

    public CustomersDTO(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.companies = new HashSet<>();
    }

    public CustomersDTO(int customerId, String customerName, Set<CompaniesDTO> companies) {
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

    public Set<CompaniesDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompaniesDTO> companies) {
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
