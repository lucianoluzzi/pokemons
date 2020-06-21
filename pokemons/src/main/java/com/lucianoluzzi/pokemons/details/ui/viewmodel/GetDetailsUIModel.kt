package com.lucianoluzzi.pokemons.details.ui.viewmodel

sealed class GetDetailsUIModel {
    object Loading: GetDetailsUIModel()
    data class Success(val data: PokemonQuery.Pokemon): GetDetailsUIModel()
    data class Error(val error: String): GetDetailsUIModel()
}