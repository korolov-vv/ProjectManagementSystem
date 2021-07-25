package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.CustomerDAO;

public class CustomersRepository extends SinglRepositoryImplementation<CustomerDAO> {

    public CustomersRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CustomerDAO.class);
    }
    /*    private static final Logger LOG = LoggerFactory.getLogger(CustomersRepository.class);
    private static final String SELECT_ALL_CUSTOMERS = "FROM CustomersDAO c";

    private final SessionFactory sessionFactory;

    private static final String SELECT_CUSTOMERS_BY_NAME = "FROM CustomersDAO c WHERE c.customerName = :customerName";

    public CustomersRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<CustomersDAO> findById(int id) {
        CustomersDAO customersDAO = new CustomersDAO();
        try (Session session = sessionFactory.openSession()) {
             customersDAO = session.get(CustomersDAO.class, id);
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
        }
        return customersDAO;
    }

    @Override
    public Optional<CustomersDAO> findByUniqueValue(String customerName) {
        List<CustomersDAO> customersDAOList = new ArrayList<>();
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<CustomersDAO> query = session.createQuery(SELECT_CUSTOMERS_BY_NAME, CustomersDAO.class);
            query.setParameter("customerName", customerName);
            customersDAOList = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
        }
        return customersDAOList.get(0);
    }

    @Override
    public void create(CustomersDAO customersDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(customersDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            if(Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(CustomersDAO customersDAO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(customersDAO);
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CustomersDAO customersDAO = session.get(CustomersDAO.class, id);
            if (customersDAO != null) {
                session.delete(customersDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
        }
    }

    @Override
    public void delete(String name) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CustomersDAO customersDAO = session.get(CustomersDAO.class, name);
            if (customersDAO != null) {
                session.delete(customersDAO);
            }
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
        }
    }

    @Override
    public List<CustomersDAO> findAll() {
        List<CustomersDAO> customersDAOList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<CustomersDAO> query = session.createQuery(SELECT_ALL_CUSTOMERS,
                    CustomersDAO.class);
            LOG.debug("Executing query: " + query.getQueryString());
            customersDAOList = query.getResultList();
            transaction.commit();
        } catch (Exception ex) {
            LOG.debug("Unable to execute query: " + ex.getMessage());
            ex.printStackTrace();
        }
        return customersDAOList;
    }*/
}
