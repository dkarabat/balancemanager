package com.balance.dao;

import com.balance.domain.User;

import java.util.List;

public interface UserDao {
    public void saveUser(User user);

    public List<User> getUser();

    public Object getUserByNme(String name);

    public User getUserEntityByID(Integer id);

    public String replenishBalance(Integer id, Double summ);
}
