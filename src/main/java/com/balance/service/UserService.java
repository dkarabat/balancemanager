package com.balance.service;

import com.balance.domain.User;

import java.util.List;

public interface UserService {
	public void addUser(User user);

	public List<User> getUsers();

    public User getUserByName(String name);

    public Double  addBalance(Integer id, Double summ);

    public User getUserEntityById(Integer id);
}
