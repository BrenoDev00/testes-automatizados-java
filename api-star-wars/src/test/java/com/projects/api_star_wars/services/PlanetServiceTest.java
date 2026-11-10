package com.projects.api_star_wars.services;

import com.projects.api_star_wars.models.Planet;
import com.projects.api_star_wars.repositories.PlanetRepository;
import org.h2.table.Plan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static com.projects.api_star_wars.constants.PlanetConstants.PLANET;
import static com.projects.api_star_wars.constants.PlanetConstants.INVALID_PLANET;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // configuração de classe de teste
public class PlanetServiceTest {
    @InjectMocks // mock real (chama service)
    private PlanetService planetService;

    @Mock // mock falso (usa uma cópia falsa do repository)
    private PlanetRepository planetRepository;

    @Test
    public void createPlanet(){
        when(this.planetRepository.save(PLANET)).thenReturn(PLANET);

        Planet createdPlanet = this.planetService.create(PLANET);

        assertThat(createdPlanet).isEqualTo(PLANET);
    }

    @Test
    public void createPlanetWithInvalidData(){
        when(this.planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> this.planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void findPlanetById(){
        UUID planetId = UUID.randomUUID();

        when(this.planetRepository.findById(planetId)).thenReturn(Optional.of(PLANET));

        Planet searchedPlanet = this.planetService.listById(planetId);

        assertThat(searchedPlanet).isNotNull();
        assertThat(searchedPlanet).isEqualTo(PLANET);
    }

    @Test
    public void findInexistentPlanetById(){
        UUID planetId = UUID.randomUUID();

        when(this.planetRepository.findById(planetId)).thenThrow(NoSuchElementException.class);

        assertThatThrownBy(() ->
                this.planetService.listById(planetId)
        ).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void findPlanetByName(){
        when(this.planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

        Planet searchedPlanet = this.planetService.listByName(PLANET.getName());

        assertThat(searchedPlanet).isNotNull();
        assertThat(searchedPlanet).isEqualTo(PLANET);
    }

    @Test
    public void findInexistentPlanetByName(){
        when(this.planetRepository.findByName(PLANET.getName())).thenThrow(NoSuchElementException.class);

        assertThatThrownBy(() ->
                this.planetService.listByName(PLANET.getName())
        ).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void listAllPlanets(){
        when(this.planetRepository.findAll()).thenReturn(List.of(PLANET));

        List<Planet> planetList = this.planetService.listAll();

        assertThat(planetList).isNotNull();
        assertThat(planetList).isEqualTo(List.of(PLANET));
    }

    @Test
    public void deleteInexistentPlanetById(){
        UUID planetId = UUID.randomUUID();

       when(this.planetRepository.findById(planetId)).thenReturn(Optional.empty());

       assertThatThrownBy(() -> this.planetService.deleteById(planetId)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void deletePlanetById(){
        UUID planetId = UUID.randomUUID();

        when(this.planetRepository.findById(planetId)).thenReturn(Optional.of(PLANET));

        assertThatCode(() ->
                this.planetService.deleteById(planetId)).doesNotThrowAnyException();
    }
}
