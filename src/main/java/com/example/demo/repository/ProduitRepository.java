package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Produit;
import com.example.demo.models.SousCategorie;



@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	List<Produit> findBySousCategorieId(Long souscategorieId);

}
