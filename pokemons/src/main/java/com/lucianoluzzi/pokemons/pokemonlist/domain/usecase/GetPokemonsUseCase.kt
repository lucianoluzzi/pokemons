package com.lucianoluzzi.pokemons.pokemonlist.domain.usecase

import com.lucianoluzzi.pokemons.pokemonlist.domain.entity.GetPokemonsResultWrapper

interface GetPokemonsUseCase {
    suspend fun invoke(): GetPokemonsResultWrapper
}