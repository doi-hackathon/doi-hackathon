package com.scan4kids.project.controllers;


import com.scan4kids.project.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {
    private UsersRepository usersDao;
    private EventsRepository eventsDao;

    public EventController(UsersRepository usersRepository, EventsRepository eventsRepository) {
        this.usersDao = usersRepository;
        this.postsDao = eventsRepository;
    }

    @GetMapping("events")
    public String eventsIndex(Model model) {
        List<Event> events = eventsDao.findAll();
        model.addAttribute("events", events);
        model.addAttribute("noEventsFound", events.size() == 0);
        return "events/index";
    }


}
