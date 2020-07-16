package com.scan4kids.project.controllers;


import com.scan4kids.project.daos.EventsRepository;
import com.scan4kids.project.daos.UsersEventsRepository;
import com.scan4kids.project.daos.UsersRepository;
import com.scan4kids.project.models.Event;
import com.scan4kids.project.models.User;
import com.scan4kids.project.models.UsersEvents;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private UsersRepository usersDao;
    private EventsRepository eventsDao;
    private UsersEventsRepository usersEventsDao;

    public EventController(UsersRepository usersRepository, EventsRepository eventsRepository, UsersEventsRepository usersEventsRepository) {
        this.usersDao = usersRepository;
        this.eventsDao = eventsRepository;
        this.usersEventsDao = usersEventsRepository;
    }

    @GetMapping("/events")
    public String eventsIndex(Model model) {
        List<Event> events = eventsDao.findAll();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UsersEvents> usersEvents = usersEventsDao.findAllByUserId(currentUser.getId());
        System.out.println("usersEvents: " +  usersEvents.get(0).getId());
        model.addAttribute("usersEvents", usersEvents);
        model.addAttribute("events", events);
        model.addAttribute("noEventsFound", events.size() == 0);
        return "events/index";
    }

    @GetMapping("/events/{id}")
    public String show(@PathVariable long id, Model model){
        Event event = eventsDao.getOne(id);
        model.addAttribute("eventId", id);
        model.addAttribute("event", event);
        return "/events/show";
    }

    @GetMapping("/events/create")
    public String createEventForm(Model eventCreateModel){
        eventCreateModel.addAttribute("event", new Event());
        return "events/create";
    }

    @PostMapping("events/create")
    public String createEvent(@ModelAttribute Event eventToCreate) {
        eventsDao.save(eventToCreate);
        return "redirect:/events";
    }

    @GetMapping("/events/{id}/edit")
    public String showEditForm(Model model, @PathVariable Long id) {
        Event eventToEdit = eventsDao.getOne(id);
        model.addAttribute("event", eventToEdit);
        return "events/edit";
    }

    @PostMapping("/events/{id}/edit")
    public String editEvent(@ModelAttribute Event eventToEdit) {
        eventsDao.save(eventToEdit);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/delete")
    public String delete(@PathVariable long id) {
        eventsDao.deleteById(id);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/volunteer")
    public String volunteerForEvent(@PathVariable long id){
        Event eventToVolunteer = eventsDao.getOne(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.getOne(currentUser.getId());
        UsersEvents userEventsToSave = new UsersEvents(user, eventToVolunteer, true);
        usersEventsDao.save(userEventsToSave);
        return "redirect:/userDashboard";
    }

    @PostMapping("/events/{id}/rsvp")
    public String rsvpForEvent(@PathVariable long id){
        Event eventToVolunteer = eventsDao.getOne(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.getOne(currentUser.getId());
        UsersEvents userEventsToSave = new UsersEvents(user, eventToVolunteer, false);
        usersEventsDao.save(userEventsToSave);
        return "redirect:/userDashboard";
    }



}