package com.lucianoluzzi.pokemons.pokemonlist.domain.usecase

interface GetPokemonsUseCase {
    suspend fun invoke(): List<PokemonsQuery.Pokemon?>
}