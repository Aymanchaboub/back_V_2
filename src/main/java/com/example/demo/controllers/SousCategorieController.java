package com.example.demo.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.SousCategorie;
import com.example.demo.services.SousCategorieService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






@RestController
@RequestMapping("/api/auth")

public class SousCategorieController {
	private final SousCategorieService sousCategorieService;

	public SousCategorieController(SousCategorieService sousCategorieService) {
		
	    this.sousCategorieService = sousCategorieService;
	}

	@GetMapping("/sous-categories")
	public List<SousCategorie> getAllSousCategories() {
		
	    return sousCategorieService.findAll();
	}

	@GetMapping("/sous-categories/{id}")
	public ResponseEntity<SousCategorie> getSousCategorieById(@PathVariable Long id) {
    	System.out.println("sfds12");

	    java.util.Optional<SousCategorie> sousCategorie = sousCategorieService.findById(id);
	    return sousCategorie.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/sous-categories")
	public SousCategorie createSousCategorie(@RequestBody SousCategorie sousCategorie) {
		System.out.println(sousCategorie.toString());
	    return sousCategorieService.save(sousCategorie);
	}

	@PutMapping("/sous-categories/{id}")
	public ResponseEntity<SousCategorie> updateSousCategorie(@PathVariable Long id, @RequestBody SousCategorie sousCategorie) {
		System.out.println("mashi");
	    java.util.Optional<SousCategorie> existingSousCategorie = sousCategorieService.findById(id);
	    if (existingSousCategorie.isPresent()) {
	        sousCategorie.setId(id);
	        return ResponseEntity.ok(sousCategorieService.save(sousCategorie));
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@DeleteMapping("/sous-categories/{id}")
	public ResponseEntity<Void> deleteSousCategorie(@PathVariable Long id) {
	    java.util.Optional<SousCategorie> sousCategorie = sousCategorieService.findById(id);
	    if (sousCategorie.isPresent()) {
	        sousCategorieService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
    @GetMapping("/sous-categories/ByID/{categorieId}")
    public List<SousCategorie> getSousCategoriesByCategorieId(@PathVariable Long categorieId) {
    	System.out.println("asj");
        return sousCategorieService.getSousCategoriesByCategorieId(categorieId);
    }
}
