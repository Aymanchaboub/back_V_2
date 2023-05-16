package com.example.demo.controllers;

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
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Commande;
import com.example.demo.models.LigneCommande;
import com.example.demo.models.User;
import com.example.demo.repository.CommandeRepository;
import com.example.demo.repository.LigneCommandeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.CommandeService;
import com.example.demo.services.LigneCommandeService;

@RestController
@RequestMapping("/api/auth")
public class LigneCommandeController {

    @Autowired
    private LigneCommandeService ligneCommandeService;
    
    @Autowired
    private LigneCommandeRepository LignecommandeReposotory;
    
    @Autowired
    private CommandeRepository commandeReposotory;
    
    @Autowired
    private CommandeService commandeService;

    @Autowired
    private UserRepository userReposotry;
    // Get all lignesCommandes
    @GetMapping("/lignescommandes")
    public ResponseEntity<List<LigneCommande>> getAllLignesCommandes(@RequestParam Long livreurId) {
        List<LigneCommande> lignesCommandes = ligneCommandeService.getAllLigneCommandes(livreurId);
        return new ResponseEntity<>(lignesCommandes, HttpStatus.OK);
    }



    // Get a single ligneCommande by id
    @GetMapping("/lignescommandes/{id}")
    public ResponseEntity<LigneCommande> getLigneCommandeById(@PathVariable Long id) {
        LigneCommande ligneCommande = ligneCommandeService.getLigneCommandeById(id);
        return new ResponseEntity<>(ligneCommande, HttpStatus.OK);
    }

    // Create a new ligneCommande
    @PostMapping("/lignescommandes")
    public ResponseEntity<LigneCommande> createLigneCommande(@RequestBody LigneCommande ligneCommande,@RequestParam Long commandeId,@RequestParam Long livreurId) {
    	Commande commande = commandeReposotory.findById(commandeId).orElse(null);
    	User livreur = userReposotry.findById(livreurId).orElse(null);

    	if (commande == null || livreur == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}

    	ligneCommande.setCommande(commande);
    	ligneCommande.setLivreur(livreur);

    	LigneCommande newLigneCommande = ligneCommandeService.createLigneCommande(ligneCommande);
    	return new ResponseEntity<>(newLigneCommande, HttpStatus.CREATED);
}

    
 // Fonction pour mettre à jour une ligne de commande dans le contrôleur Spring Boot
    @PutMapping("/lignescommandes/{id}/complete")
    public ResponseEntity<LigneCommande> updateLigneCommande(@PathVariable Long id, @RequestBody boolean isComplete) {
        LigneCommande updatedLigneCommande = ligneCommandeService.updateLigneCommande(id, isComplete);
        Long  commande_id=updatedLigneCommande.getCommande().getId();
        Commande commande=commandeReposotory.getById(commande_id);
        commande.setStatutCommande(true);
        commandeService.updateCommande(commande_id, commande, commande.getUser());
        return new ResponseEntity<>(updatedLigneCommande, HttpStatus.OK);
    }
    
 // Méthode pour récupérer le commandeId à partir d'un ligneCommandeId
    @GetMapping("/lignescommandes/{id}/getcommande")
    public Long getCommandeIdByLigneCommandeId(@PathVariable Long id) {
        return ligneCommandeService.getCommandeIdByLigneCommandeId(id);
    }






    // Méthode pour récupérer le livreurId à partir d'un ligneCommandeId
    @GetMapping("/lignescommande/getlivreur")
    public Long getLivreurIdByLigneCommandeId(Long ligneCommandeId) {
        LigneCommande ligneCommande = LignecommandeReposotory.findById(ligneCommandeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne de commande introuvable"));

        return ligneCommande.getLivreur().getId();
    }
    



    // Delete a ligneCommande
    @DeleteMapping("/lignescommandes/{id}")
    public ResponseEntity<Void> deleteLigneCommande(@PathVariable Long id) {
        ligneCommandeService.deleteLigneCommande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
