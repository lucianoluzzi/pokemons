package com.lucianoluzzi.pokemons.details.data

interface PokemonDetailsRepository {
    @Throws(Exception::class)
    suspend fun fetchDetails(pokemonName: String): PokemonQuery.Data?
}