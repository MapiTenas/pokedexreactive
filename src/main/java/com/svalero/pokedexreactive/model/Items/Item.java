package com.svalero.pokedexreactive.model.Items;


import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    String name;
	List<ItemAttribute> attributes;
	List<ItemEffect> effect_entries;
    Sprites sprites;
    String effectString;
    String attributesString;
	String defaultSprite;

    public void setDataForTable(){
        ArrayList<String> attributesNames = new ArrayList<String>();
        for (ItemAttribute iAtt : this.getAttributes()){
            attributesNames.add(iAtt.getName());
        }
        this.attributesString = attributesNames.toString().replaceAll("\\[|\\]", "");

        ArrayList<String> effectText = new ArrayList<String>();
        for(ItemEffect iEff : this.getEffect_entries()){
            effectText.add(iEff.getShort_effect());
        }
        this.effectString = effectText.toString().replaceAll("\\[|\\]", "");
        this.defaultSprite = this.sprites.getDefaultValue();
        System.out.println("Sprites VAL: " + this.sprites.getDefaultValue());
        System.out.println("Sprite: " + this.defaultSprite);
    }

}
