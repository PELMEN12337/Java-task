package com.example.contactlist.controller;

import com.example.contactlist.model.Contact;
import com.example.contactlist.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository repository;

    @GetMapping
    public String getAllContacts(Model model) {
        model.addAttribute("contacts", repository.findAll());
        return "contacts";
    }

    @GetMapping("/add")
    public String addContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    @PostMapping("/save")
    public String saveOrUpdateContact(@ModelAttribute Contact contact) {
        if (contact.getId() != null && contact.getId() > 0) {
            // Если у контакта есть ID, значит это обновление
            repository.update(contact);
        } else {
            // Если ID нет, значит это создание нового контакта
            repository.save(contact);
        }
        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String editContactForm(@PathVariable Long id, Model model) {
        Contact contact = repository.findById(id);
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    @PostMapping("/update")
    public String updateContact(@ModelAttribute Contact contact) {
        repository.update(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        repository.delete(id);
        return "redirect:/contacts";
    }
}
