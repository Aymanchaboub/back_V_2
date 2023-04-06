package com.example.demo.controllers;

import com.example.demo.models.Cart;
import com.example.demo.models.Commande;
import com.example.demo.services.CartService;
import com.example.demo.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private CartService cartService;

    @GetMapping("/commande/{userId}")
    public List<Commande> getCommandes(@PathVariable Long userId) {
        return commandeService.getAllCommandes(userId);
    }

    @GetMapping("/commande/{id}/{userId}")
    public Optional<Commande> getCommandeById(@PathVariable Long id, @PathVariable Long userId) {
        return commandeService.getCommandeById(id, userId);
    }

    @PostMapping("/commande/{userId}/{cart_id}")
    public Commande createCommande(@RequestBody Commande commandeData, @RequestParam Long userId, @PathVariable Long cart_id) {
        Cart cart = new Cart();
        cart.setId(cart_id);
        commandeData.setCart(cart);
        return commandeService.createCommande(commandeData, userId);
    }







    @PutMapping("/commande/{id}/{userId}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commande, @PathVariable Long userId) {
        return commandeService.updateCommande(id, commande, userId);
    }

    @DeleteMapping("/commande/{id}/{userId}")
    public void deleteCommande(@PathVariable Long id, @PathVariable Long userId) {
        commandeService.deleteCommande(id, userId);
    }
}
