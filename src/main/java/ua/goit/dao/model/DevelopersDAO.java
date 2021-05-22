package ua.goit.dao.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DevelopersDAO {
    int developerId;
    String firstName;
    String lastName;
    String gender;
    int age;
    int experienceInYears;
    int companyId;
    int projectId;
    int salary;

    public DevelopersDAO() {
    }

    public DevelopersDAO(int developerId, String firstName, String lastName, String gender, int age,
                         int experienceInYears, int companyId, int projectId, int salary) {
        this.developerId = developerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.experienceInYears = experienceInYears;
        this.companyId = companyId;
        this.projectId = projectId;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developers{" +
                "developerId=" + developerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", experienceInYears=" + experienceInYears +
                ", companyId=" + companyId +
                ", projectId=" + projectId +
                ", salary=" + salary +
                '}';
    }
}
