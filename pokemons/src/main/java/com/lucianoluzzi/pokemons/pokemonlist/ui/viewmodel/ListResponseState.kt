package com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel

import com.lucianoluzzi.pokemons.pokemonlist.ui.adapter.PokemonEntryUIModel

sealed class ListResponseState {
    data class Success(val value: List<PokemonEntryUIModel>): ListResponseState()
    data class Error(val error: String? = null): ListResponseState()
}