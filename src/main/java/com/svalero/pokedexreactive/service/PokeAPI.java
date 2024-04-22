package com.svalero.pokedexreactive.service;

import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;
import com.svalero.pokedexreactive.model.Pokemon.PokemonType;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeAPI {

   @GET("/api/v2/type/{type}")
   Observable<PokemonType> getType(@Path("type") String type);

   @GET("/api/v2/pokemon/{pokemon}")
   Observable<PokemonInfo> getPokemon(@Path("pokemon") String name);
   
   
}
