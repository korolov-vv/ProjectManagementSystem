package ua.goit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevelopersDTO {
    int developerId;
    String firstName;
    String lastName;
    String gender;
    int age;
    int experienceInYears;
    int companyId;
    int projectId;
    int salary;

    public DevelopersDTO() {
    }

    public DevelopersDTO(int developerId, String firstName, String lastName, String gender, int age,
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
