package ua.goit.command;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.DevelopersRepository;
import ua.goit.view.Console;

public class SelectSumSalary implements Command {
    private DatabaseConnectionManager connectionManager;
    private Console console;

    public SelectSumSalary(DatabaseConnectionManager connectionManager, Console console) {
        this.connectionManager = connectionManager;
        this.console = console;
    }

    @Override
    public void process() {
        selectSumSalaryForProject();
    }

    @Override
    public String commandName() {
        return "1";
    }

    private void selectSumSalaryForProject() {
        DevelopersRepository devRepo = new DevelopersRepository(connectionManager);
        console.write("Enter projectId: ");
        String projectId = console.read();

        console.write(String.format("The sum for the project %s = %s", projectId,
                devRepo.countSumSalary(Integer.parseInt(projectId))));
    }
}
