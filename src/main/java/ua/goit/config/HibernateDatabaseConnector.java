package ua.goit.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateDatabaseConnector {
    private static SessionFactory sessionFactory;

    public static synchronized void init() {
        MetadataSources source = new MetadataSources(new StandardServiceRegistryBuilder().configure().build());
        final Metadata metadata = source.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static synchronized void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
