package com.svalero.pokedexreactive.model.Items;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategory {
    List<Item> items;
    
}
