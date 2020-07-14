package com.scan4kids.project.controllers;


import com.scan4kids.project.daos.EventsRepository;
import com.scan4kids.project.daos.UsersRepository;
import com.scan4kids.project.models.Event;
import com.scan4kids.project.models.User;
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

//    @GetMapping("/events/{id}/volunteer")
//    public String showVolunteeredEvents(Model model, @PathVariable long id){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        System.out.println("Current User: " + currentUser.getUsername());
//        User user = usersDao.getOne(currentUser.getId());
//        List <Event> usersEvents = user.getEvents();
//        System.out.println("usersEvents" + usersEvents.size());
////        User userEventsVolunteered = usersDao.;
////        Event eventToVolunteer = eventsDao.;
////        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        List <Event> volunteeredEvents = currentUser.
////        model.addAttribute("usersEvents", );
//        return "";
//    }

    @PostMapping("/events/{id}/volunteer")
    public String volunteerForEvent(@PathVariable long id){
        Event eventToVolunteer = eventsDao.getOne(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.getOne(currentUser.getId());
        List <Event> usersEvents = user.getEvents();
        usersEvents.add(eventToVolunteer);
        user.setEvents(usersEvents);
        usersDao.save(user);
        return "redirect:/userDashboard";
    }

    @PostMapping("/events/{id}/rsvp")
    public String rsvpForEvent(@PathVariable long id){
        Event eventToRsvp = eventsDao.getOne(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.getOne(currentUser.getId());
        List <Event> usersEventsRsvp = user.getEvents();
        usersEventsRsvp.add(eventToRsvp);
        user.setEvents(usersEventsRsvp);
        usersDao.save(user);
        return "redirect:/userDashboard";
    }



}