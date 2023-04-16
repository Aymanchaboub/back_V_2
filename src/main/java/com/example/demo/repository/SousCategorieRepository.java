package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.SousCategorie;


@Repository
public interface SousCategorieRepository extends JpaRepository<SousCategorie, Long> {

    @Query("SELECT s FROM SousCategorie s WHERE s.categorie.id = :categorieId")
	List<SousCategorie> findByCategorieId(Long categorieId);
    
}