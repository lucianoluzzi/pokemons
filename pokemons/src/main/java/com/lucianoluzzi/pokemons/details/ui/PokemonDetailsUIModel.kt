package com.lucianoluzzi.pokemons.details.ui

import com.lucianoluzzi.pokemons.details.domain.entity.Evolution

data class PokemonDetailsUIModel(
    val number: String,
    val name: String,
    val classification: String,
    val image: String?,
    val types: List<String>,
    val resistances: List<String>,
    val weaknesses: List<String>,
    val evolutions: List<Evolution>
) {
    val contentDescription = "Pokémon number $number"
}