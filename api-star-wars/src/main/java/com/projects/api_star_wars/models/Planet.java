package com.projects.api_star_wars.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String climate;
    private String terrain;

    public Planet(){}

    public Planet(String name, String climate, String terrain){
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public UUID getId(){return this.id;}

    public String getName(){return this.name;}

    public String getClimate(){return this.climate;}

    public String getTerrain(){return this.terrain;}

    public void setId(UUID id){this.id = id;}

    public void setName(String name){this.name = name;}

    public void setClimate(String climate){this.climate = climate;}

    public void setTerrain(String terrain){this.terrain = terrain;}
}
