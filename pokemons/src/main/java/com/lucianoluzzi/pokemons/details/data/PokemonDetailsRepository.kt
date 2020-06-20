package com.lucianoluzzi.pokemons.details.data

interface PokemonDetailsRepository {
    suspend fun fetchDetails(): PokemonQuery.Data?
}