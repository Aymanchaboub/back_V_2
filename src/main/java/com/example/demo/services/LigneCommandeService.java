package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.LigneCommande;
import com.example.demo.repository.LigneCommandeRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class LigneCommandeService {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public List<LigneCommande> getAllLigneCommandes(Long livreurId) {
        return ligneCommandeRepository.findByLivreurId(livreurId);
    }

    public LigneCommande getLigneCommandeById(Long id) {
        java.util.Optional<LigneCommande> optionalLigneCommande = ligneCommandeRepository.findById(id);
        if (optionalLigneCommande.isPresent()) {
            return optionalLigneCommande.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne de commande introuvable");
        }
    }

    public List<LigneCommande> getLigneCommandesByIsComplete(boolean isComplete) {
        return ligneCommandeRepository.findByIsComplete(isComplete);
    }

    public LigneCommande createLigneCommande(LigneCommande ligneCommande) {
        return ligneCommandeRepository.save(ligneCommande);
    }

 // Fonction pour mettre à jour une ligne de commande dans la base de données
    public LigneCommande updateLigneCommande(Long id, boolean isComplete) {
        java.util.Optional<LigneCommande> optionalLigneCommande = ligneCommandeRepository.findById(id);
        if (optionalLigneCommande.isPresent()) {
            LigneCommande existingLigneCommande = optionalLigneCommande.get();
            existingLigneCommande.setIsComplete(isComplete);
            ligneCommandeRepository.save(existingLigneCommande);
            return existingLigneCommande;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne de commande introuvable");
        }
    }


    public void deleteLigneCommande(Long id) {
        java.util.Optional<LigneCommande> optionalLigneCommande = ligneCommandeRepository.findById(id);
        if (optionalLigneCommande.isPresent()) {
            ligneCommandeRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne de commande introuvable");
        }
    }
    public Long getCommandeIdByLigneCommandeId(Long id) {
        return ligneCommandeRepository.findCommandeIdByLigneCommandeId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne de commande introuvable"));
    }
    

}
