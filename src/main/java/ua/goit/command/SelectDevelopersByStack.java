package ua.goit.command;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.DevelopersRepository;
import ua.goit.view.Console;

public class SelectDevelopersByStack implements Command {
    private DatabaseConnectionManager connectionManager;
    private Console console;

    public SelectDevelopersByStack(DatabaseConnectionManager connectionManager, Console console) {
        this.connectionManager = connectionManager;
        this.console = console;
    }


    @Override
    public void process() {
        selectDevelopersByStack();
    }

    @Override
    public String commandName() {
        return "3";
    }

    private void selectDevelopersByStack() {
        DevelopersRepository devRepo = new DevelopersRepository(connectionManager);
        console.write("Enter developer's technology from list: Java, C++, C#, JS: ");
        String stack = console.read();

        System.out.println("The developers, who works on " + stack + " :");
        devRepo.selectDevelopersByStack(stack).forEach(System.out::println);
    }
}
