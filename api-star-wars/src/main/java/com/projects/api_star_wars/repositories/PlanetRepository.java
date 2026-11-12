package com.projects.api_star_wars.repositories;

import com.projects.api_star_wars.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, UUID> {
    Optional<Planet> findByName(String planetName);
}

