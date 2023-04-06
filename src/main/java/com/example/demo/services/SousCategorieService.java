package com.example.demo.services;

import java.util.List;


import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.SousCategorie;
import com.example.demo.repository.SousCategorieRepository;



@Service
public class SousCategorieService {

    private final SousCategorieRepository sousCategorieRepository;

    public SousCategorieService(SousCategorieRepository sousCategorieRepository) {
        this.sousCategorieRepository = sousCategorieRepository;
    }

    public List<SousCategorie> findAll() {
        return sousCategorieRepository.findAll();
    }

    public Optional<SousCategorie> findById(Long id) {
        return sousCategorieRepository.findById(id);
    }

    public SousCategorie save(SousCategorie sousCategorie) {
        return sousCategorieRepository.save(sousCategorie);
    }

    public void deleteById(Long id) {
        sousCategorieRepository.deleteById(id);
    }
    public List<SousCategorie> getSousCategoriesByCategorieId(Long categorieId) {
        return sousCategorieRepository.findByCategorieId(categorieId);
    }

}