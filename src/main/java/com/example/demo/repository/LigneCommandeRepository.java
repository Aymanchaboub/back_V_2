package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.LigneCommande;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
    List<LigneCommande> findByIsComplete(boolean isComplete);
    @Query("SELECT lc, c, l FROM LigneCommande lc JOIN lc.commande c JOIN lc.livreur l WHERE lc.livreur.id = :livreurId")

    List<LigneCommande> findByLivreurId(@Param("livreurId")Long livreurId);
    
    @Query("SELECT l.commande.id FROM LigneCommande l WHERE l.id = :id")
    Optional<Long> findCommandeIdByLigneCommandeId(@Param("id") Long id);    

}
