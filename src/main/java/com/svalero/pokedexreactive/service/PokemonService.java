package com.svalero.pokedexreactive.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svalero.pokedexreactive.model.Pokemon.PokemonInfo;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonService {

    private String BASE_URL = "https://pokeapi.co/api/v2/";
    private PokeAPI pokeAPI;

    public PokemonService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build(); 
    
        Gson gsonParser = new GsonBuilder()
            .setLenient()
            .create();
        
        Retrofit retrofit = new Retrofit.Builder() 
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonParser))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
        this.pokeAPI = retrofit.create(PokeAPI.class);    
    }

    public Observable<PokemonInfo> getPokemons(String inputType) {
        Observable<String> pokemonNamesObservable = this.pokeAPI.getType(inputType)
            .flatMap(pokeType -> Observable.fromIterable(pokeType.getPokemon()))
            .map(pokemonSlot -> pokemonSlot.getPokemon().getName());
        Observable<PokemonInfo> pokemonInfoObservable = pokemonNamesObservable
            .flatMap(pokemonName -> this.pokeAPI.getPokemon(pokemonName));
        return pokemonInfoObservable;
    }

    public Observable<Integer> getTotalPokemonCount(String inputType) {
        return this.pokeAPI.getType(inputType)
            .flatMap(pokeType -> Observable.fromIterable(pokeType.getPokemon()))
            .map(pokemonSlot -> pokemonSlot.getPokemon().getName())
            .toList()
            .map(pokemonNamesList -> pokemonNamesList.size())
            .toObservable();
    }
    
}
