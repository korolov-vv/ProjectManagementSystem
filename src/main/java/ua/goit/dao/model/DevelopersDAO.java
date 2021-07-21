package ua.goit.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "developers")
public class DevelopersDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id", nullable = false)
    private int developerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;
    @Column(name = "experience_in_years")
    private int experienceInYears;
    @Column(name = "company_id", nullable = false)
    private int companyId;
    @Column(name = "salary")
    private int salary;
    @Column(name = "developer_email", nullable = false)
    private String developerEmail;
    @OneToMany(mappedBy = "developersDAO", cascade = CascadeType.ALL)
    private Set<SkillsDAO> skills = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "developers_on_projects",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    Set<ProjectsDAO> projects = new HashSet<>();

    public DevelopersDAO() {
    }

    public DevelopersDAO(int developerId, String firstName, String lastName, String gender, int age,
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

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
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

    public Set<SkillsDAO> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillsDAO> skills) {
        this.skills = skills;
    }

    public Set<ProjectsDAO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDAO> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "DevelopersDAO{" +
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
