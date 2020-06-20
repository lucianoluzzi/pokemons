package com.lucianoluzzi.pokemons.entities

data class PokemonEntry (
    val number: Int,
    val name: String,
    val types: List<String>? = null,
    val image: String? = null
)