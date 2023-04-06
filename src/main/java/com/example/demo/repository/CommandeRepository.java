package com.example.demo.repository;

import com.example.demo.models.Commande;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByUser_Id(Long userId);
    Optional<Commande> findByIdAndUser_Id(Long id, Long userId);
    void deleteByIdAndUser_Id(Long id, Long userId);
}
