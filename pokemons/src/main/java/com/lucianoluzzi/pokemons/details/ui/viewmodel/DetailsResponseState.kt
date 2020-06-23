package com.lucianoluzzi.pokemons.details.ui.viewmodel

import com.lucianoluzzi.pokemons.details.ui.PokemonDetailsUIModel

sealed class DetailsResponseState {
    data class Success(val data: PokemonDetailsUIModel): DetailsResponseState()
    data class Error(val error: String): DetailsResponseState()
}