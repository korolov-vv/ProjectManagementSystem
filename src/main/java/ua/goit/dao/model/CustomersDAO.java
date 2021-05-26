package ua.goit.dao.model;

public class CustomersDAO {
    private long customerId;
    private String customerName;
    private long projectId;

    public CustomersDAO() {
    }

    public CustomersDAO(long customerId, String customerName, long projectId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.projectId = projectId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", numberOfDevelopers=" + projectId +
                '}';
    }
}
