package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.CompanyDAO;

public class CompaniesRepository extends SinglRepositoryImplementation<CompanyDAO> {

    public CompaniesRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CompanyDAO.class);
    }

    /*private final static Logger LOG = LoggerFactory.getLogger(CompaniesRepository.class);

    private final SessionFactory sessionFactory;

    private static final String SELECT_ALL_COMPANIES = "FROM CompaniesDAO AS c";

    private static final String SELECT_BY_NAME = "FROM CompaniesDAO c WHERE c.companyName = :companyName";

    public CompaniesRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<CompaniesDAO> findById(int id) {
        CompaniesDAO companiesDAO = new CompaniesDAO();
        try (Session session = sessionFactory.openSession()) {
            companiesDAO = session.get(CompaniesDAO.class, id);
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return companiesDAO;
    }

    @Override
    public Optional<CompaniesDAO> findByUniqueValue(String companyName) {
        List<CompaniesDAO> companiesDAOList = new ArrayList<>();
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<CompaniesDAO> query = session.createQuery(SELECT_BY_NAME, CompaniesDAO.class);
            query.setParameter("companyName", companyName);
            companiesDAOList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
        return companiesDAOList.get(0);
    }

    @Override
    public void create(CompaniesDAO companiesDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(companiesDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(CompaniesDAO companiesDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(companiesDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(String name) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CompaniesDAO companiesDAO = session.get(CompaniesDAO.class, name);
            if (companiesDAO != null) {
                session.delete(companiesDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CompaniesDAO companiesDAO = session.get(CompaniesDAO.class, id);
            if (companiesDAO != null) {
                session.delete(companiesDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public List<CompaniesDAO> findAll() {
        List<CompaniesDAO> companiesDAOList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<CompaniesDAO> query = session.createQuery(SELECT_ALL_COMPANIES,
                    CompaniesDAO.class);
            LOG.debug("Executing query: " + query.getQueryString());
            companiesDAOList = query.getResultList();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug("Unable to execute query: " + ex.getMessage());
            ex.printStackTrace();
        }
        return companiesDAOList;
    }*/
}
