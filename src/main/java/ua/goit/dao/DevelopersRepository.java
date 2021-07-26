package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.DeveloperDAO;

public class DevelopersRepository extends SingleRepositoryImplementation<DeveloperDAO> {

    public DevelopersRepository(SessionFactory sessionFactory) {
        super(sessionFactory, DeveloperDAO.class);
    }

    }
