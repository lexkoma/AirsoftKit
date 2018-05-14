package com.panteon.stock.db;

import java.sql.Driver;
import java.util.Objects;
// class which is MySql itself
public class DataSource {
    private Driver jdbcDriver;
    private String connectionUrl;
    private String login;
    private String password;

    public DataSource(Driver jdbcDriver, String connectionUrl, String login, String password) {
        this.jdbcDriver = jdbcDriver;
        this.connectionUrl = connectionUrl;
        this.login = login;
        this.password = password;
    }

    public Driver getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(Driver jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object dataSource) {
        boolean result = false;
        if (dataSource instanceof DataSource) {
            result = getJdbcDriver().getClass().getName()
                    .equals(((DataSource) dataSource).getJdbcDriver().getClass().getName())
                    && getConnectionUrl().equals(((DataSource) dataSource).getConnectionUrl())
                    && getLogin().equals(((DataSource) dataSource).getLogin())
                    && getPassword().equals(((DataSource) dataSource).getPassword());
        }
        return result;
    }
}
