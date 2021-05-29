package ua.goit;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.DevelopersRepository;
import ua.goit.service.developers.DevelopersService;
import ua.goit.util.PropertiesLoader;

public class Application {
    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        propertiesLoader.loadPropertiesFile("application.properties");

        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager(
                propertiesLoader.getProperty("host"),
                propertiesLoader.getProperty("database.name"),
                propertiesLoader.getProperty("username"),
                propertiesLoader.getProperty("password"));

        DevelopersRepository devRepo = new DevelopersRepository(databaseConnectionManager);
        DevelopersService ds = new DevelopersService(devRepo);

        System.out.println("Вывести на консоль:");

        System.out.println("1. зарплату(сумму) всех разработчиков отдельного проекта");
        System.out.println(devRepo.countSumSalary(2));

        System.out.println("2. список разработчиков отдельного проекта");

        System.out.println("3. список всех Java разработчиков");

        System.out.println("4. список всех middle разработчиков");

        System.out.println("5. список проектов в следующем формате: дата создания - название проекта - количество разработчиков на этом проекте");

/*        ds.create(new DevelopersDTO(0, "Petro", "Chursin", "m", 23,
                5, 1, 2500, "h@gmail.com", List.of(), List.of()));
        ds.update(new DevelopersDTO(0, "Vasil", "Chursin", "m", 23,
                5, 3, 500, "g@gmail.com", List.of(), List.of()));*/


        /*try (Connection connection = databaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT developer_id, first_name, last_name," +
                     "gender, age, experience_in_years, company_id, salary" +
                     "FROM developers WHERE first_name='Vasil' AND last_name='Chursin';")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(DevelopersConverter.toDeveloper(resultSet));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
    }
}
