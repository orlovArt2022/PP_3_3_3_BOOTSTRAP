package ru.orlov.bootstrap.bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.orlov.bootstrap.bootstrap.model.AppUserRoles;
import ru.orlov.bootstrap.bootstrap.model.Role;
import ru.orlov.bootstrap.bootstrap.model.User;
import ru.orlov.bootstrap.bootstrap.service.RoleService;
import ru.orlov.bootstrap.bootstrap.service.UserService;
import ru.orlov.bootstrap.bootstrap.service.UserServiceImpl;


import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        Arrays.stream(AppUserRoles.values())
                .map(x -> x.toString())
                .forEach(x -> roleService.addRole(new Role(x)));

        User startUser = new User("admin", "admin",30, "admin", "admin");
        startUser.addRoleToUser(roleService.findByName("ROLE_ADMIN"));
        startUser.addRoleToUser(roleService.findByName("ROLE_USER"));
        userService.addUser(startUser);
//        System.out.println(startUser.getStringRoles());

    }

    @GetMapping()
    public String showAllUsers(Model model, Authentication authentication) {
        model.addAttribute("singleUser", userService.findByEmail(authentication.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("emptyUser", new User());
        List<Role> allRoles = (List<Role>) roleService.getAllRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/adminMainModal";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PutMapping()
    public String doEdit(@ModelAttribute("userToEdit") User newUser) {
        userService.editUser(newUser);
        return "redirect:/admin";
    }

   @DeleteMapping()
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
