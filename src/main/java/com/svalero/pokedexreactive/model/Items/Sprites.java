package com.svalero.pokedexreactive.model.Items;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sprites {
    @SerializedName("default")
    String defaultValue;
    
}
