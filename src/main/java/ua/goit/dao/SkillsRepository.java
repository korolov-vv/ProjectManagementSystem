package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.SkillDAO;

public class SkillsRepository extends SingleRepositoryImplementation<SkillDAO> {

    public SkillsRepository(SessionFactory sessionFactory) {
        super(sessionFactory, SkillDAO.class);
    }

}
