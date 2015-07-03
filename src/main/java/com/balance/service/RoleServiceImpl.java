package com.balance.service;


import com.balance.dao.RoleDAO;
import com.balance.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDao;

    @Override
    public void addRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public List<Role> getRole() {
        return roleDao.getRole();
    }
}
