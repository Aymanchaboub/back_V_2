package com.example.demo.controllers;

import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Categorie;
import com.example.demo.services.CategorieService;




@RestController
@RequestMapping("/api/auth")

public class CategorieController {

	
    private final CategorieService categorieService;
    
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("/categories")
    public List<Categorie> getAllCategories() {
        return categorieService.findAll();
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        java.util.Optional<Categorie> categorie = categorieService.findById(id);
        return categorie.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/categories")
    public Categorie createCategorie(@RequestBody Categorie categorie) {
    	System.out.println("kjsgdfgdskfg");
        return categorieService.save(categorie);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        java.util.Optional<Categorie> existingCategorie = categorieService.findById(id);
        System.out.println(categorie.getImage());
        if (existingCategorie.isPresent()) {
            categorie.setId(id);
            return ResponseEntity.ok(categorieService.save(categorie));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        java.util.Optional<Categorie> categorie = categorieService.findById(id);
        if (categorie.isPresent()) {
            categorieService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}