package com.lucianoluzzi.pokemons.details.ui

data class PokemonDetailsUIModel(
    val number: String,
    val name: String,
    val classification: String,
    val image: String?
) {
    val contentDescription = "Pokémon number $number"
}