package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.CompanyDAO;

public class CompaniesRepository extends SingleRepositoryImplementation<CompanyDAO> {

    public CompaniesRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CompanyDAO.class);
    }

    }
