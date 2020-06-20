package com.lucianoluzzi.pokemons.data

import PokemonsQuery

interface PokemonListRepository {
    suspend fun fetchPokemons(): List<PokemonsQuery.Pokemon?>?
}
