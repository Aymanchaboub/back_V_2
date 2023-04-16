package com.example.demo.services;
import java.time.LocalDateTime;
import java.util.Date;
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
    @Autowired
    UserRepository userRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
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
    public String getNomByUserid(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

          return optionalUser.get().getUsername();

      }

      public String getEmailByUserid(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
          return optionalUser.get().getEmail();

      }      
      public LocalDateTime  getDateCreation(Long id) {
          return commandeRepository.findDateCreationById(id);
      }
}
