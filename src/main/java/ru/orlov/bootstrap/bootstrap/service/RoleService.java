package ru.orlov.bootstrap.bootstrap.service;


import ru.orlov.bootstrap.bootstrap.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void addRole(Role role);

    Role findByName(String name);
}
