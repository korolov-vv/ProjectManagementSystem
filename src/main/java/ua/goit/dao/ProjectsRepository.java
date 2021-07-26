package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.ProjectDAO;

public class ProjectsRepository extends SingleRepositoryImplementation<ProjectDAO> {

    public ProjectsRepository(SessionFactory sessionFactory) {
        super(sessionFactory, ProjectDAO.class);
    }
    }
