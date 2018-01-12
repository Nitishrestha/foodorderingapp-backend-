package com.foodorderingapp.config;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DBConnection {

    private static SessionFactory sessionFactory;

    @Autowired
    public DBConnection(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Connection getConnection() throws SQLException {
        return sessionFactory
                .getSessionFactoryOptions()
                .getServiceRegistry()
                .getService(ConnectionProvider.class)
                .getConnection();
    }
}
