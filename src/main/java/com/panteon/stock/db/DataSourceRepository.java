package com.panteon.stock.db;

import com.panteon.stock.tools.PropertiesReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

//singleton which instantiate and return MySql DB
public final class DataSourceRepository {
    private final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";
    private static String url = PropertiesReader.getPropertyFromFile("db.connectionUrl");
    private static String login = PropertiesReader.getPropertyFromFile("db.user");
    private static String pass = PropertiesReader.getPropertyFromFile("db.password");


    private DataSourceRepository() {
    }

    public static  DataSource getDefault(){
        return getMySqlLocalHost();
    }

    public static DataSource getMySqlLocalHost (){
        Driver sqlDriver;
        try {
            sqlDriver = new com.mysql.jdbc.Driver();
        } catch (SQLException e) {
            // TODO Develop Custom Exceptions
            throw new RuntimeException(FAILED_JDBC_DRIVER);
        }

        return new DataSource(sqlDriver, url, login, pass);

    }


}
