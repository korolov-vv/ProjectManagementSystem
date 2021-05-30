package ua.goit.controller;

import ua.goit.command.*;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.view.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainController {
    DatabaseConnectionManager connectionManager;
    private Console console;
    private List<Command> commands;

    public MainController(DatabaseConnectionManager connectionManager, Console console) {
        this.connectionManager = connectionManager;
        this.console = console;
        this.commands = new ArrayList<>(Arrays.asList(
                new Help(console),
                new SelectSumSalary(connectionManager,console),
                new SelectDevelopersFromProject(connectionManager, console),
                new SelectDevelopersByStack(connectionManager, console),
                new SelectdevelopersByLevel(connectionManager, console),
                new SelectAllProjects(connectionManager, console)));
    }

    public void run() {
        console.write("Welcome to application!");
        doCommand();
    }

    private void doCommand() {
        boolean isNotExit = true;
        while (isNotExit) {
            console.write("Please enter a command or help to receive a command list");
            String inputCommand = console.read();
            for (Command command : commands) {
                if (command.canProcess(inputCommand)) {
                    command.process();
                    break;
                } else if (inputCommand.equalsIgnoreCase("exit")) {
                    console.write("Good Bye!");
                    isNotExit = false;
                    break;
                }
            }
        }
    }
}
