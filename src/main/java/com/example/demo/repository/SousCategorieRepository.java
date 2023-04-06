package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.SousCategorie;


@Repository
public interface SousCategorieRepository extends JpaRepository<SousCategorie, Long> {
    List<SousCategorie> findByCategorieId(Long categorieId);
    
}