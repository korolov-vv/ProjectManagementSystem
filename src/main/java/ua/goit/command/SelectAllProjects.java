package ua.goit.command;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.service.projects.ProjectsConverter;
import ua.goit.view.Console;

import java.util.stream.Collectors;

public class SelectAllProjects implements Command {
    private HikariDataSource dataSource;
    private Console console;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;
    private CustomersAndCompaniesRepository customersAndCompaniesRepository;

    public SelectAllProjects(HikariDataSource dataSource, Console console) {
        this.dataSource = dataSource;
        this.console = console;
    }


    @Override
    public void process() {
        selectAllProjects();
    }

    @Override
    public String commandName() {
        return "5";
    }

    private void selectAllProjects() {
        ProjectsRepository projRepo = new ProjectsRepository(dataSource);
        System.out.println(("There are all projects: "));
        projRepo.findAllProjects().stream()
                .map(ProjectsConverter::fromProject)
                .collect(Collectors.toList()).forEach(projectsDTO -> System.out.println(
                projectsDTO.getDateOfBeginning() + " - " + projectsDTO.getProjectName() +
                        " - " + projectsDTO.getNumberOfDevelopers()));
    }
}
