package ua.goit.command;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.ProjectsRepository;
import ua.goit.service.projects.ProjectsConverter;
import ua.goit.view.Console;

import java.util.stream.Collectors;

public class SelectAllProjects implements Command {
    private DatabaseConnectionManager connectionManager;
    private Console console;

    public SelectAllProjects(DatabaseConnectionManager connectionManager, Console console) {
        this.connectionManager = connectionManager;
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
        ProjectsRepository projRepo = new ProjectsRepository(connectionManager);
        System.out.println(("There are all projects: "));
        projRepo.findAllProjects().stream()
                .map(ProjectsConverter::fromProject)
                .collect(Collectors.toList()).forEach(projectsDTO -> System.out.println(
                projectsDTO.getDateOfBeginning() + " - " + projectsDTO.getProjectName() +
                        " - " + projectsDTO.getNumberOfDevelopers()));
    }
}
