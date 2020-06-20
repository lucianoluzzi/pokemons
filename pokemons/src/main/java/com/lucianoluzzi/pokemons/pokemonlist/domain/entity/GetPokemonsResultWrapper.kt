package com.lucianoluzzi.pokemons.pokemonlist.domain.entity

sealed class GetPokemonsResultWrapper {
    data class Data(val data: List<PokemonsQuery.Pokemon?>): GetPokemonsResultWrapper()
    data class Error(val error: String? = null): GetPokemonsResultWrapper()
}