package com.balance.dao;

import com.balance.domain.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleDao")
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveRole(Role role) {

        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }

    @Override
    public List<Role> getRole() {
        List<Role> userlist = sessionFactory.getCurrentSession()
                .createCriteria(Role.class).list();
        return userlist;
    }
}
