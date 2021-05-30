package ua.goit.command;

import ua.goit.view.Console;

public class Help implements Command {
    private Console console;

    public Help(Console console) {
        this.console = console;
    }

    @Override
    public void process() {
        console.write("help - show a list of commands");
        console.write("1 - Select sum salary for project");
        console.write("2 - Select developers on project");
        console.write("3 - Select developers by stack");
        console.write("4 - Select developers by level");
        console.write("5 - Select all projects");
    }

    @Override
    public String commandName() {
        return "help";
    }
}
