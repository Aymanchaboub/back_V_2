package com.example.demo.controllers;

import com.example.demo.models.ContactForm;
import com.example.demo.services.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class ContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    // Create a new ContactForm
    @PostMapping("/contact")
    public ResponseEntity<ContactForm> createContactForm(@RequestBody ContactForm contactForm) {
        ContactForm newContactForm = contactFormService.createContactForm(contactForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContactForm);
    }

    // Retrieve all produits
    @GetMapping("")
    public ResponseEntity<List<ContactForm>> getAllContactForms() {
        List<ContactForm> ContactForms = contactFormService.getAllContactForms();
        return ResponseEntity.ok(ContactForms);
    }

    // Retrieve a produit by id
    @GetMapping("contact/{id}")
    public ResponseEntity<ContactForm> getContactFormById(@PathVariable Long id) {
        ContactForm contactForm = contactFormService.getContactFormById(id);
        if (contactForm == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contactForm);
    }

    // Update an existing produit
    @PutMapping("contact/{id}")
    public ResponseEntity<ContactForm> updateContactForm(@PathVariable Long id, @RequestBody ContactForm contactForm) {
        ContactForm updatedContactForm =contactFormService.updateContactForm(id, contactForm);
        if (updatedContactForm == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedContactForm);
    }

    // Delete a produit
    @DeleteMapping("contact/{id}")
    public ResponseEntity<Void> deleteContactForm(@PathVariable Long id) {
        boolean deleted = contactFormService.deleteContactForm(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
