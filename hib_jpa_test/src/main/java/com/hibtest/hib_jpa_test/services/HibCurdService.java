package com.hibtest.hib_jpa_test.services;


import com.hibtest.hib_jpa_test.dao.UserDao;
import com.hibtest.hib_jpa_test.entities.User;

import java.util.List;

public class HibCurdService {
    UserDao userDao = new UserDao();
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public User getUser(int id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<User> getUserByNamedQuery(String name) {
        return userDao.getUsersByNamedQuery(name);
    }
}
