package com.example.demo.services;

import java.util.List;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Categorie;
import com.example.demo.repository.CategorieRepository;



@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> findById(Long id) {
        return categorieRepository.findById(id);
    }

    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteById(Long id) {
        categorieRepository.deleteById(id);
    }

}