package ua.goit;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.controller.MainController;
import ua.goit.util.PropertiesLoader;
import ua.goit.view.Console;

public class Application {
    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        propertiesLoader.loadPropertiesFile("application.properties");

        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(
                propertiesLoader.getProperty("host"),
                propertiesLoader.getProperty("database.name"),
                propertiesLoader.getProperty("username"),
                propertiesLoader.getProperty("password"));

        Console console = new Console();
        MainController controller = new MainController(databaseConnectionManager, console);
        controller.run();
    }
}
