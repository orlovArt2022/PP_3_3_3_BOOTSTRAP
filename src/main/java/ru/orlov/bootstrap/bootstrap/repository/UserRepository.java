package ru.orlov.bootstrap.bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orlov.bootstrap.bootstrap.model.User;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
