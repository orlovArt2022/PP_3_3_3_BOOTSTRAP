package ru.orlov.bootstrap.bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orlov.bootstrap.bootstrap.model.Role;


import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role, Long> {

    Optional<Role> findByName(String name);
}
