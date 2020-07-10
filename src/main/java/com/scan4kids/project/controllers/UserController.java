package com.scan4kids.project.controllers;

import com.scan4kids.project.daos.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UsersRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

}
