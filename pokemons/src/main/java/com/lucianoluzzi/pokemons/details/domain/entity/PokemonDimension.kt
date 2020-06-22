package com.lucianoluzzi.pokemons.details.domain.entity

data class PokemonDimension(
    val from: String,
    val to: String) {

    val description = "from $from to $to"
}