package com.example.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Produit;
import com.example.demo.repository.ProduitRepository;



@Service
public class ProduitService {
    
    @Autowired
    private ProduitRepository produitRepository;

    // Create a new produit
    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Retrieve all produits
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // Retrieve a produit by id
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    // Update an existing produit
    public Produit updateProduit(Long id, Produit produit) {
        Produit existingProduit = produitRepository.findById(id).orElse(null);
        if (existingProduit == null) {
            return null;
        }
        existingProduit.setNom(produit.getNom());
        existingProduit.setPrix(produit.getPrix());
        existingProduit.setImage(produit.getImage());
        existingProduit.setSousCategorie(produit.getSousCategorie());
        return produitRepository.save(existingProduit);
    }

    // Delete a produit
    public boolean deleteProduit(Long id) {
        Produit existingProduit = produitRepository.findById(id).orElse(null);
        if (existingProduit == null) {
            return false;
        }
        produitRepository.delete(existingProduit);
        return true;
    }
}