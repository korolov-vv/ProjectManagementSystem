package ua.goit.command;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.DevelopersRepository;
import ua.goit.view.Console;

public class SelectdevelopersByLevel implements Command {
    private DatabaseConnectionManager connectionManager;
    private Console console;

    public SelectdevelopersByLevel(DatabaseConnectionManager connectionManager, Console console) {
        this.connectionManager = connectionManager;
        this.console = console;
    }


    @Override
    public void process() {
        selectDevelopersByLevel();
    }

    @Override
    public String commandName() {
        return "4";
    }

    private void selectDevelopersByLevel() {
        DevelopersRepository devRepo = new DevelopersRepository(connectionManager);
        console.write("Enter developer's level from list : Junior, Middle, Senior: ");
        String level = console.read();

        System.out.println("There are " + level + " developers:");
        devRepo.selectDevelopersByLevel(level).forEach(System.out::println);
    }
}
