package com.example.demo.services;

import com.example.demo.models.ContactForm;
import com.example.demo.repository.ContactFormRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactFormService {

    @Autowired
    private ContactFormRepository contactFormRepository ;

    // Create a new  ContactForm
    public ContactForm createContactForm(ContactForm contactForm) {
        return contactFormRepository.save(contactForm);
    }

    // Retrieve all  ContactForm
    public List<ContactForm> getAllContactForms() {
        return contactFormRepository.findAll();
    }

    // Retrieve a  ContactForm by id
    public ContactForm getContactFormById(Long id) {
        return contactFormRepository.findById(id).orElse(null);
    }

    // Update an existing  ContactForm
    public ContactForm updateContactForm(Long id, ContactForm contactForm) {
        ContactForm existingContactForm = contactFormRepository.findById(id).orElse(null);
        if (existingContactForm == null) {
            return null;
        }
        existingContactForm.setName(contactForm.getName());
        existingContactForm.setEmail(contactForm.getEmail());
        existingContactForm.setSubject(contactForm.getSubject());
        existingContactForm.setMessage(contactForm.getMessage());
        return contactFormRepository.save( existingContactForm);
    }

    // Delete a produit
    public boolean deleteContactForm(Long id) {
        ContactForm existingContactForm =  contactFormRepository.findById(id).orElse(null);
        if (existingContactForm == null) {
            return false;
        }
        contactFormRepository.delete(existingContactForm);
        return true;
    }

    }