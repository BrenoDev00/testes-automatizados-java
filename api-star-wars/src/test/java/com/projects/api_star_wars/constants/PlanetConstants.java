package com.projects.api_star_wars.constants;

import com.projects.api_star_wars.models.Planet;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("name", "climate", "terrain");
    public static final Planet INVALID_PLANET = new Planet("", "", "");
}
