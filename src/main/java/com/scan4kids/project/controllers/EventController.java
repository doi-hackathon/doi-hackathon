package com.scan4kids.project.controllers;


import com.scan4kids.project.daos.EventsRepository;
import com.scan4kids.project.daos.UsersRepository;
import com.scan4kids.project.models.Event;
import com.scan4kids.project.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class EventController {
    private UsersRepository usersDao;
    private EventsRepository eventsDao;

    public EventController(UsersRepository usersRepository, EventsRepository eventsRepository) {
        this.usersDao = usersRepository;
        this.eventsDao = eventsRepository;
    }

    @GetMapping("/events")
    public String eventsIndex(Model model) {
        List<Event> events = eventsDao.findAll();
        model.addAttribute("events", events);
        model.addAttribute("noEventsFound", events.size() == 0);
        return "events/index";
    }

    @GetMapping("events/create")
    public String createEvent(@ModelAttribute Event eventToCreate) {
        User currentUser = usersDao.getOne(1L);
//        this will be used when security is configured
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        eventToCreate.setUsers((List<User>) currentUser);
        Event newEvent = eventsDao.save(eventToCreate);
        return "redirect:/events";
    }



}