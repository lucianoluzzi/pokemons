package com.lucianoluzzi.pokemons.domain.usecase

interface GetPokemonsUseCase {
    suspend fun invoke(): List<PokemonsQuery.Pokemon?>
}