package com.lucianoluzzi.pokemons.details.data

interface PokemonDetailsRepository {
    suspend fun fetchDetails(pokemonName: String): PokemonQuery.Data?
}