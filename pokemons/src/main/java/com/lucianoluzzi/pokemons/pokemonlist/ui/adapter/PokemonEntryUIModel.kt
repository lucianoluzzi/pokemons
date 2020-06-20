package com.lucianoluzzi.pokemons.pokemonlist.ui.adapter

data class PokemonEntryUIModel (
    val number: String,
    val name: String
) {
    val contentDescription = "Pokémon number $number, $name"
}