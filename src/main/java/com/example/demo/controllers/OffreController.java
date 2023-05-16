package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Offre;
import com.example.demo.services.OffreService;

@RestController
@RequestMapping("/api/auth")
public class OffreController {
    @Autowired
    private OffreService offreService;

    @GetMapping("/offres")
    public List<Offre> getAllOffres() {
        return offreService.getAllOffres();
    }
    @GetMapping("/offres/{id}")
    public Offre getOffreById(@PathVariable(value = "id") Long id) {
        return offreService.getOffreById(id);
    }
    @PutMapping("/offres/{id}")
    public Offre updateOffre(@PathVariable(value = "id") Long id, @Valid @RequestBody Offre offreDetails) {
        return offreService.updateOffre(id, offreDetails);
    }

    @DeleteMapping("/offres/{id}")
    public ResponseEntity<?> deleteOffre(@PathVariable(value = "id") Long id) {
        offreService.deleteOffre(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/offres/getproduit/{id}")
    public Long getproductid(@PathVariable Long id)
    {
    	Offre offre=offreService.getOffreById(id);
    	return offre.getProduit().getId();
    }
}