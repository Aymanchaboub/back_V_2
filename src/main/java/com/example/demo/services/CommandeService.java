package com.example.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Commande;
import com.example.demo.models.User;
import com.example.demo.repository.CommandeRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CommandeService {
    
    @Autowired
    private CommandeRepository commandeRepository;
    
    @Autowired
    private UserRepository userRepositry;

    public List<Commande> getAllCommandes(Long userId) {
        return commandeRepository.findByUser_Id(userId);
    }

    public Optional<Commande> getCommandeById(Long id, Long userId) {
        return commandeRepository.findByIdAndUser_Id(id, userId);
    }

    public Commande createCommande(Commande commande, Long userId) {
        System.out.println("Creating commande for user with ID: " + userId);
        System.out.println("commandeservice"+commande);
        
        User user = userRepositry.findById(userId).orElse(null);
        if (user != null) {
            commande.setUser(user);
            return commandeRepository.save(commande);
        } else {
            // handle the case where the user is not found
            return null;
        }
    }



    public Commande updateCommande(Long id, Commande commande, Long userId) {
        Optional<Commande> existingCommande = commandeRepository.findByIdAndUser_Id(id, userId);
        if (existingCommande.isPresent()) {
            Commande updatedCommande = existingCommande.get();
            updatedCommande.setCart(commande.getCart());
            updatedCommande.setStatutCommande(commande.isStatutCommande());
            return commandeRepository.save(updatedCommande);
        } else {
            return null;
        }
    }


    public void deleteCommande(Long id, Long userId) {
        commandeRepository.deleteByIdAndUser_Id(id, userId);
    }
}
