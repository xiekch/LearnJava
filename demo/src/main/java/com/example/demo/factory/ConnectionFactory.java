package com.example.demo.factory;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.ClassPathResource;

public class ConnectionFactory implements FactoryBean {
    private Connection connection;

    public ConnectionFactory() throws Exception {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("db.properties");
        FileInputStream in = new FileInputStream(resource.getFile());
        properties.load(in);

        Class.forName(properties.getProperty("driver"));

        String url = properties.getProperty("url");
        Connection connection = DriverManager.getConnection(url, properties);
        this.connection = connection;
    }

    @Override
    public Object getObject() throws Exception {
        return this.connection;
    }

    @Override
    public Class getObjectType() {
        return this.connection.getClass();
    }
}