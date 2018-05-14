package com.panteon.stock.services;

import com.panteon.stock.dao.UserDao;
import com.panteon.stock.dto.LoginDto;
import com.panteon.stock.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
    private UserDao userDao;

    public LoginService() {
        this.userDao = new UserDao(); // bad practice
    }

    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isLogged(LoginDto loginDto) {
        System.out.println("here");
        boolean result = false;
        List<User> users = new ArrayList<>();
        // list of all users with loginDTO.getLogin()
        // only 1 element is expected
        try {
            users = userDao.getByField(User.USER_LOGIN, loginDto.getLogin());
            System.out.println(users);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if ((users.size() == 1) && (users.get(0).getPassword().equals(loginDto.getPassword()))) {
            result = true;
        }
        return result;
    }






}
