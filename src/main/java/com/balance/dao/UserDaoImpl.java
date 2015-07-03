package com.balance.dao;

import com.balance.domain.Role;
import com.balance.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        user.setBalance(0.0);
        Date date = new Date();
        user.setReg_date(date);
        user.setEnabled(true);
        Role role = (Role) sessionFactory.getCurrentSession().get(Role.class, 2);
        user.setRole(role);

        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<User> getUser() {
        List userlist = sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
        return userlist;
    }

    @Override
    public User getUserByNme(String login) {
        List<User> userList = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.username = :login");
        query.setParameter("login", login);
        userList = query.list();
        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;
    }

    @Override
    public User getUserEntityByID(Integer id) {
        User user = (User)  sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    @Override
    public String replenishBalance(Integer id, Double summ) {
        System.out.println("replanish  !!!");
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        System.out.println("user name = " + user.getUsername());
        user.setBalance(user.getBalance() + summ);
        System.out.println("balance = " + user.getBalance());
        sessionFactory.getCurrentSession().flush();
        return "updated";
    }

}
