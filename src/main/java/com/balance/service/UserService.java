package com.balance.service;

import com.balance.domain.User;

import java.util.List;

public interface UserService {
	public void addUser(User user);

	public List<User> getUser();

    public User getUserByName(String name);

    public String  replenishBalance(Integer id, Double summ);

    public User getUserEntityById(Integer id);
}
