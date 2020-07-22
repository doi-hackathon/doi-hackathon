package com.scan4kids.project.controllers;

import com.scan4kids.project.models.Contact;
import com.scan4kids.project.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContactForm(@ModelAttribute Contact contactToBeSaved) {
        emailService.prepareAndSend(contactToBeSaved, "Thank you from S.C.A.N.!", "Thanks for reaching out to us. We are excited to learn of your interest in our organization and will be in touch with you soon!");
        return "contact-done";
    }

}
