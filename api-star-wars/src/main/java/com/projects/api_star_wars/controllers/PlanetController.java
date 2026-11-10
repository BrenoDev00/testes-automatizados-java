package com.projects.api_star_wars.controllers;

import com.projects.api_star_wars.models.Planet;
import com.projects.api_star_wars.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/planets")
public class PlanetController {
    @Autowired
    private PlanetService planetService;

    @GetMapping
    ResponseEntity<List<Planet>> getAll(){
        List<Planet> planets = this.planetService.listAll();

        return ResponseEntity.ok().body(planets);
    }

    @GetMapping("/{planetId}")
    public ResponseEntity<Planet> getById(@PathVariable("planetId") UUID planetId){
        try{
            Planet planet = this.planetService.listById(planetId);
            return ResponseEntity.ok().body(planet);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{planetName}")
    public ResponseEntity<Planet> getByName(@PathVariable("planetName") String planetName){
        try{
            Planet planet = this.planetService.listByName(planetName);
            return ResponseEntity.ok().body(planet);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<Planet> post(@RequestBody Planet planet){
        Planet createdPlanet = this.planetService.create(planet);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlanet);
    }

    @DeleteMapping("/{planetId}")
    ResponseEntity<Void> delete(@PathVariable("planetId") UUID planetId){
        try{
            this.planetService.deleteById(planetId);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
