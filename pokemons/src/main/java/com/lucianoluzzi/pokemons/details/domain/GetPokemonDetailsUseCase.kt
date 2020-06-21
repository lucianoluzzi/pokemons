package com.lucianoluzzi.pokemons.details.domain

import com.lucianoluzzi.pokemons.details.domain.entity.GetDetailsResultWrapper

interface GetPokemonDetailsUseCase {
    suspend fun invoke(): GetDetailsResultWrapper
}