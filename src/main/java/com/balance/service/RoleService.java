package com.balance.service;


import com.balance.domain.Role;

import java.util.List;

public interface RoleService {

    public void addRole(Role role);

    public List<Role> getRole();
}
