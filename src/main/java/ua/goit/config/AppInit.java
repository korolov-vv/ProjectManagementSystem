package ua.goit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit implements ServletContextListener {
    private final static Logger LOG = LoggerFactory.getLogger(AppInit.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.debug("Init DatabaseConnectionManager");
        DatabaseConnectionManager.init();
        LOG.debug("Init HibernateDatabaseConnector");
        HibernateDatabaseConnector.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateDatabaseConnector.destroy();
    }

}
