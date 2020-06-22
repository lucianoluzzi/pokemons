package com.lucianoluzzi.pokemons.details.domain.usecase

import com.lucianoluzzi.pokemons.details.domain.entity.GetDetailsResultWrapper

interface GetPokemonDetailsUseCase {
    suspend fun invoke(pokemonName: String): GetDetailsResultWrapper
}