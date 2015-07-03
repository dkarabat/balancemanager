package com.balance.dao;


import com.balance.domain.Role;

import java.util.List;

public interface RoleDAO {

    public void saveRole(Role role);

    public List<Role> getRole();
}
