package ua.goit.dto;

import java.util.List;

public class DevelopersDTO {
    private long developerId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private int experienceInYears;
    private int companyId;
    private int salary;
    private String developerEmail;
    private List<SkillsDTO> skills;
    private List<ProjectsDTO> projects;

    public DevelopersDTO() {
    }

    public DevelopersDTO(long developerId, String firstName, String lastName, String gender, int age,
                         int experienceInYears, int companyId, int salary, String developerEmail) {
        this.developerId = developerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.experienceInYears = experienceInYears;
        this.companyId = companyId;
        this.salary = salary;
        this.developerEmail = developerEmail;
    }

    public DevelopersDTO(long developerId, String firstName, String lastName, String gender, int age,
                         int experienceInYears, int companyId, int salary, String developerEmail,
                         List<SkillsDTO> skills, List<ProjectsDTO> projects) {
        this.developerId = developerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.experienceInYears = experienceInYears;
        this.companyId = companyId;
        this.salary = salary;
        this.developerEmail = developerEmail;
        this.skills = skills;
        this.projects = projects;
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDeveloperEmail() {
        return developerEmail;
    }

    public void setDeveloperEmail(String developerEmail) {
        this.developerEmail = developerEmail;
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
                ", salary=" + salary +
                ", developerEmail='" + developerEmail + '\'' +
                '}';
    }
}
