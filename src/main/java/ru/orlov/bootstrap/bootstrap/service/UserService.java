package ru.orlov.bootstrap.bootstrap.service;



import ru.orlov.bootstrap.bootstrap.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User newUser);

    User getByIdUser(Long id);

    User findByEmail(String email);

}
