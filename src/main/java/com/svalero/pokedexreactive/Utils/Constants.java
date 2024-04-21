package com.svalero.pokedexreactive.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Constants {
    
    public static final List<String> pokeTypes = Collections.unmodifiableList(
    new ArrayList<String>() {{
        add("normal");
        add("fighting");
        add("flying");
        add("poison");
        add("ground");
        add("rock");
        add("bug");
        add("ghost");
        add("steel");
        add("fire");
        add("water");
        add("grass");
        add("electric");
        add("psychic");
        add("ice");
        add("dragon");
        add("dark");
        add("fairy");
    }});

}
