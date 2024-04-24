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

    public static final List<String> itemTypes = Collections.unmodifiableList(
        new ArrayList<String>() {{
            add("stat-boosts");
            add("effort-drop");
            add("medicine");
            add("other");
            add("in-a-pinch");
            add("picky-healing");
            add("type-protection");
            add("baking-only");
            add("colectibles");
            add("evolution");
            add("spelunking");
            add("held-items");
            add("choice");
            add("effort-training");
            add("bad-held-items");
            add("training");
            add("type-enhancement");
            add("event-items");
        }});
    

}
