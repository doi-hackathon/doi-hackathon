package com.scan4kids.project.controllers;

import com.scan4kids.project.daos.EventsRepository;
import com.scan4kids.project.daos.UsersRepository;
import com.scan4kids.project.models.Event;
import com.scan4kids.project.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private UsersRepository users;
    private PasswordEncoder passwordEncoder;
    private EventsRepository eventsDao;
    private UsersRepository usersDao;


    public UserController(UsersRepository users, PasswordEncoder passwordEncoder, UsersRepository usersDao) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.usersDao = usersDao;
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/userDashboard")
    public String showUserDashboard(Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.getOne(currentUser.getId());
        List <Event> usersEvents = user.getEvents();
        System.out.println("usersEvents" + usersEvents.size());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentEmail", currentUser);
        model.addAttribute("usersEvents", usersEvents);
        return "users/userDashboard";
    }

}
