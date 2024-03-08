package com.electro.util.listeners;


import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private final String FACTORY = "factory";


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("electro");
        sce.getServletContext().setAttribute(FACTORY, factory);
        System.out.println("Context is initialized. Factory is created");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory factory = (EntityManagerFactory) sce.getServletContext().getAttribute(FACTORY);
        AbandonedConnectionCleanupThread.checkedShutdown();
        factory.close();
        System.out.println("Context is destroyed. Factory is closed");
    }
}
