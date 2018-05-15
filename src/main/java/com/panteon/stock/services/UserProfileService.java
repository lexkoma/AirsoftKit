package com.panteon.stock.services;

import com.panteon.stock.dao.UserDao;
import com.panteon.stock.dto.UserDto;
import com.panteon.stock.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserProfileService {
    private UserDao userDao;

    public UserProfileService() {
        this.userDao = new UserDao();  // bad practise
    }

    public UserProfileService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDto getUserDto(String login) {
        User user;
        if (isExistLogin(login)) {
            try {
                user = userDao.getByField(User.USER_LOGIN, login).get(0);
                return new UserDto(user.getId(),
                        user.getName(),
                        user.getLogin(),
                        user.getEmail(),
                        user.getPassword());

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public boolean setUserDto(UserDto userDto) {
        User user = new User(userDto.getId(),
                userDto.getName(), userDto.getLogin(),
                userDto.getEmail(), userDto.getPassword());
        System.out.println("in setUser " + user.toString());
        boolean result = false;

        if (userDto.getId() < 0) {
            if (!isExistLogin(userDto.getLogin())) {
                try {
                    userDao.insert(user);
                    System.out.println("user" + user + " added");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                result = true;
            }
        } else {
            try {
                userDao.update(user);
                System.out.println("user " + user + "updated");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result = true;
        }
        return result;
    }

    public boolean isExistLogin(String login) {
        boolean result = true;
        List<User> users = null;
        try {
            users = userDao.getByField(User.USER_LOGIN, login);
            if (users.isEmpty()) {
                System.out.println("Login " + login + " doesn't exists");
                result = false;
            } else {
                System.out.println("Login " + login + " exists already");
            }

        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("User not found. SQLException: " + e.getMessage());
        }
        return result;
    }


    public void deleteUserDto(long id) {
        try {
            userDao.deleteById(id);
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("User not found. SQLException: " + e.getMessage());
        }
    }

    public void deleteUserDto(UserDto userDto) {
        deleteUserDto(userDto.getId());
    }
}
