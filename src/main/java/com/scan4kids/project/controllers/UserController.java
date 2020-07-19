package com.scan4kids.project.controllers;

import com.scan4kids.project.daos.EventsRepository;
import com.scan4kids.project.daos.UsersEventsRepository;
import com.scan4kids.project.daos.UsersRepository;
import com.scan4kids.project.models.User;
import com.scan4kids.project.models.UsersEvents;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;
    private EventsRepository eventsDao;
    private UsersEventsRepository usersEventsDao;



    public UserController(UsersRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
     }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, Errors validation, Model model){
        User existingUsername = usersDao.findByUsername(user.getUsername());
        User existingEmail = usersDao.findByEmail(user.getEmail());

        if(existingUsername != null) {
            validation.rejectValue("username", "user.username", user.getUsername() + " is already in use.");
        }

        if(!user.getPassword().equals(user.getPasswordToConfirm())) {
            validation.rejectValue("password",user.getPassword(), "Your passwords do not match.");
        }

        if(existingEmail != null) {
            validation.rejectValue("email", "user.email", user.getEmail() + " is already in use.");
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "users/sign-up";
        }

        String hash = passwordEncoder.encode(user.getPassword());
        String hashForConfirm = passwordEncoder.encode(user.getPasswordToConfirm());
        user.setPassword(hash);
        user.setPasswordToConfirm(hashForConfirm);

        usersDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String showUserDashboard(Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.getOne(currentUser.getId());
        List <UsersEvents> usersEvents = user.getUserEvents();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentEmail", currentUser);
        model.addAttribute("usersEvents", usersEvents);
        return "users/dashboard";
    }
}
