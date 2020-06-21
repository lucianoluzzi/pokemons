package com.lucianoluzzi.pokemons.details.domain.entity

import PokemonQuery

sealed class GetDetailsResultWrapper {
    data class Success(val data: PokemonQuery.Data): GetDetailsResultWrapper()
    data class Error(val error: String?): GetDetailsResultWrapper()
}