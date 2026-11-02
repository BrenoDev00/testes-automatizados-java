package com.projects.api_star_wars.services;

import com.projects.api_star_wars.models.Planet;
import com.projects.api_star_wars.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PlanetService {
    @Autowired
    private PlanetRepository planetRepository;

    public List<Planet> listAll(){
        return this.planetRepository.findAll();
    }

    public Planet listById(UUID planetId){
        return this.planetRepository.findById(planetId)
                .orElseThrow(() -> new NoSuchElementException("Planeta não econtrado."));
    }

    public Planet create(Planet planet){
        return this.planetRepository.save(planet);
    }
}
