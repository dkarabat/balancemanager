package com.balance.service;

import com.balance.dao.UserDao;
import com.balance.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> getUser() {
        return userDao.getUser();
    }

    @Override
    public User getUserByName(String name) {
        return (User) userDao.getUserByNme(name);
    }

    @Override
    public String replenishBalance(Integer id, Double summ) {
        return userDao.replenishBalance(id, summ);
    }

    @Override
    public User getUserEntityById(Integer id) {
        return userDao.getUserEntityByID(id);
    }

}
