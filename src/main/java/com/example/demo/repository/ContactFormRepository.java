package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ContactForm;

public interface ContactFormRepository extends JpaRepository<ContactForm, Long> {

}
