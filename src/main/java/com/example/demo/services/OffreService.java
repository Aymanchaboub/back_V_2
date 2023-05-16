package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Offre;
import com.example.demo.repository.OffreRepository;

@Service
public class OffreService {
    @Autowired
    private OffreRepository offreRepository;

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }
    public Offre getOffreById(Long id) {
        return offreRepository.findById(id)
                .orElseThrow();
    }
    public Offre updateOffre(Long id, Offre offreDetails) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow();

        offre.setDateDeCreation(offreDetails.getDateDeCreation());
        offre.setDateDExtraction(offreDetails.getDateDExtraction());
        offre.setPourcentage(offreDetails.getPourcentage());
        offre.setProduit(offreDetails.getProduit());

        Offre updatedOffre = offreRepository.save(offre);
        return updatedOffre;
    }
    public void deleteOffre(Long id) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow();

        offreRepository.delete(offre);
    }
}
