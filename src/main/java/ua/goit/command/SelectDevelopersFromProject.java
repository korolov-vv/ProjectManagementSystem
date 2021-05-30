package ua.goit.command;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.DevelopersRepository;
import ua.goit.view.Console;

public class SelectDevelopersFromProject implements Command {
    private DatabaseConnectionManager connectionManager;
    private Console console;

    public SelectDevelopersFromProject(DatabaseConnectionManager connectionManager, Console console) {
        this.connectionManager = connectionManager;
        this.console = console;
    }

    @Override
    public void process() {
        selectDevelopersFromProject();
    }

    @Override
    public String commandName() {
        return "2";
    }

    private void selectDevelopersFromProject() {
        DevelopersRepository devRepo = new DevelopersRepository(connectionManager);
        console.write("Enter projectId: ");
        String projectId = console.read();

        System.out.println("The developers, who works on " + projectId + " :");
        devRepo.selectDevelopersOnProject(Integer.parseInt(projectId)).forEach(System.out::println);
    }
}
