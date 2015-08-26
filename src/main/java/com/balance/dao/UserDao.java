package com.balance.dao;

import com.balance.domain.User;

import java.util.List;

public interface UserDao {
    public void saveUser(User user);

    public List<User> getUsers();

    public Object getUserByName(String name);

    public User getUserEntityByID(Integer id);

    public Double addBalance(Integer id, Double summ);
}
