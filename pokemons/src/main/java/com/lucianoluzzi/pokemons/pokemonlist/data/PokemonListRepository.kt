package com.lucianoluzzi.pokemons.pokemonlist.data

import PokemonsQuery

interface PokemonListRepository {
    suspend fun fetchPokemons(): List<PokemonsQuery.Pokemon?>?
}
