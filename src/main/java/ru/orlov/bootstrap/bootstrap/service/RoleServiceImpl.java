package ru.orlov.bootstrap.bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.orlov.bootstrap.bootstrap.model.Role;
import ru.orlov.bootstrap.bootstrap.repository.RoleRepository;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }
}
