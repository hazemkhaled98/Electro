package com.electro.presentation.listeners;


import com.electro.persistence.Database;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ResourcesCleaner implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Database.close();
    }
}
