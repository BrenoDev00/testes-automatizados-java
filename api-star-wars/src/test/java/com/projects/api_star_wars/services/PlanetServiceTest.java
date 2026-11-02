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

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static com.projects.api_star_wars.constants.PlanetConstants.PLANET;
import static com.projects.api_star_wars.constants.PlanetConstants.INVALID_PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
}
