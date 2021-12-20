package com.foracademy.dao;

import com.foracademy.dao.Class.ClassDao;
import com.foracademy.dao.Class.ClassDaoImplementation;
import com.foracademy.dao.address.AddressDao;
import com.foracademy.dao.address.AddressDaoImplementation;
import com.foracademy.dao.factory.FactoryDao;
import com.foracademy.dao.factory.FactoryDaoImplementation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    private DaoFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() throws IOException {
        // Read database configuration from properties here
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/dao.properties"));
        String _url = properties.getProperty("DATABASE_URL");
        String _username = properties.getProperty("USERNAME");
        String _password = properties.getProperty("PASSWORD");

        return new DaoFactory(_url, _username, _password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.username, this.password);
    }

    public AddressDao getAddressDao() {
        return new AddressDaoImplementation(this);
    }

    public ClassDao getClassDao() {
        return new ClassDaoImplementation();
    }

    public FactoryDao getFactoryDao() {
        return new FactoryDaoImplementation();
    }

}
