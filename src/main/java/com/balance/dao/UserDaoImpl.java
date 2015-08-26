package com.balance.dao;

import com.balance.domain.Role;
import com.balance.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    final static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void saveUser(User user) {
        Date date = new Date();
        user.setReg_date(date);
        user.setEnabled(true);
        Role role = (Role) sessionFactory.getCurrentSession().get(Role.class, 2);
        user.setRole(role);

        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<User> getUsers() {
        List userlist = sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
        return userlist;
    }

    @Override
    public User getUserByName(String login) {
        List<User> userList;
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
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    @Override
    public Double addBalance(Integer id, Double summ) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        log.info("user name = {}", user.getUsername());
        double sum;
        sum = new BigDecimal(user.getBalance() + summ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        user.setBalance(sum);
        log.info("balance = {}", user.getBalance());
        sessionFactory.getCurrentSession().flush();
        return user.getBalance();
    }

}
