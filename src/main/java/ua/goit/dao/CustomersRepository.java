package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.CustomerDAO;

public class CustomersRepository extends SingleRepositoryImplementation<CustomerDAO> {

    public CustomersRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CustomerDAO.class);
    }

}
