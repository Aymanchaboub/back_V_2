package com.example.demo.controllers;

import com.example.demo.models.Cart;
import com.example.demo.models.Commande;
import com.example.demo.models.User;
import com.example.demo.repository.CommandeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.CartService;
import com.example.demo.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userrep;
    @Autowired
    private CommandeRepository commandereposotry;

    @GetMapping("/commande")
    public List<Commande> getCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/commande/{id}/{userId}")
    public Optional<Commande> getCommandeById(@PathVariable Long id, @PathVariable Long userId) {
        return commandeService.getCommandeById(id, userId);
    }
    
    @GetMapping("/commande/{id}")
    public Optional<Commande> getCommandeByIdOnly(@PathVariable Long id) {
        return commandereposotry.findById(id);
    }

    @PostMapping("/commande/{userId}/{cart_id}")
    public Commande createCommande(@RequestBody Commande commandeData, @RequestParam Long userId, @PathVariable Long cart_id) {
        Cart cart = new Cart();
        cart.setId(cart_id);
        commandeData.setCart(cart);
        commandeData.setDateCreation(LocalDateTime.now());
        return commandeService.createCommande(commandeData, userId);
    }
    @GetMapping("/commande/{userId}/nom")
    public Map<String, String> getNomByUserid(@PathVariable Long userId) {
        Optional<User> optionalUser = userrep.findById(userId);
        Map<String, String> result = new HashMap<>();
        result.put("nom", optionalUser.get().getUsername());
        return result;
    }

    
    @GetMapping("/commande/{userId}/email")
    public ResponseEntity<String> getEmailByUserid(@PathVariable Long userId) {
        String email = commandeService.getEmailByUserid(userId);
        return ResponseEntity.ok("\"" + email + "\"");
    }








    @PutMapping("/commande/{id}/{userId}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commande, @PathVariable Long userId) {
        return commandeService.updateCommande(id, commande, userId);
    }

    @DeleteMapping("/commande/{id}/{userId}")
    public void deleteCommande(@PathVariable Long id, @PathVariable Long userId) {
        commandeService.deleteCommande(id, userId);
    }
    @GetMapping("commande/{id}/dateCreation")
    public ResponseEntity<LocalDateTime > getDateCreation(@PathVariable Long id) {
        LocalDateTime dateCreation = commandeService.getDateCreation(id);
        return ResponseEntity.ok(dateCreation);
    }
    
}
