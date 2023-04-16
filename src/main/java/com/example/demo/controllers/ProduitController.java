package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Produit;
import com.example.demo.models.SousCategorie;
import com.example.demo.services.ProduitService;

@RestController
@RequestMapping("/api/auth")
public class ProduitController {
    
    @Autowired
    private ProduitService produitService;

    // Create a new produit
    @PostMapping("/produits")
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
    	System.out.println(produit.toString());
        Produit newProduit = produitService.createProduit(produit);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduit);
    }

    // Retrieve all produits
    @GetMapping("/produits")
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }

    // Retrieve a produit by id
    @GetMapping("/produits/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        Produit produit = produitService.getProduitById(id);
        if (produit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produit);
    }

    // Update an existing produit
    @PutMapping("/produits/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        Produit updatedProduit = produitService.updateProduit(id, produit);
        if (updatedProduit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduit);
    }

    // Delete a produit
    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        boolean deleted = produitService.deleteProduit(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    // Update a produit's price with a discount
    @PutMapping("/produits/{id}/updatePrice/{pourcentage}")
    public ResponseEntity<Void> updateProductPrice(@PathVariable Long id, @PathVariable double pourcentage,@RequestParam("dateExtraction") String dateExtractionStr) {
        LocalDate dateExtraction = LocalDate.parse(dateExtractionStr);
    	produitService.updateProductPrice(id, pourcentage,dateExtraction);
        return ResponseEntity.ok().build();
    }
    
    // Retrieve the price history of a produit
    @GetMapping("/produits/{id}/priceHistory")
    public ResponseEntity<Float> getProductPriceHistory(@PathVariable Long id) {
        float prixProduits = produitService.getProductPriceHistory(id);
        return ResponseEntity.ok(prixProduits);
    }
    @GetMapping("/produits/ByID/{souscategorieId}")
    public List<Produit> getSousCategoriesByCategorieId(@PathVariable Long souscategorieId) {
    	System.out.println(souscategorieId);
        return produitService.getProduitsByCategorieId(souscategorieId);
    }
}