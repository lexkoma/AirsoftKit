package com.panteon.stock.dao;

import com.panteon.stock.entity.AEntity;
import com.panteon.stock.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends ADaoCRUD<User> {

    private final static String LOGIN_FIELDNAME = "Login";

    public UserDao() {
        super("user","user_id" );
    }

    @Override
    protected User createEntity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(id, name, login, email, password);
    }

    @Override
    protected List<Object> getEntityParams(String operation, User user) {
        List<Object> params = new ArrayList<>();
        switch (operation) {
            case CREATE:
                params.add(user.getId());
                params.add(user.getName());
                params.add(user.getLogin());
                params.add(user.getEmail());
                params.add(user.getPassword());
                break;
            case UPDATE:
                params.add(user.getName());
                params.add(user.getEmail());
                params.add(user.getPassword());
                params.add(user.getId());
                break;
        }
        return params;
    }

}
