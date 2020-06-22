package com.lucianoluzzi.pokemons.details.ui

import com.lucianoluzzi.pokemons.details.domain.entity.Evolution
import com.lucianoluzzi.pokemons.details.domain.entity.PokemonDimension
import com.lucianoluzzi.pokemons.details.domain.entity.Type

data class PokemonDetailsUIModel(
    val number: String,
    val name: String,
    val classification: String,
    val image: String?,
    val types: List<Type>,
    val resistances: List<Type>,
    val weaknesses: List<Type>,
    val evolutions: List<Evolution>,
    val height: PokemonDimension,
    val weight: PokemonDimension
) {
    val contentDescription = "Pokémon number $number"
}