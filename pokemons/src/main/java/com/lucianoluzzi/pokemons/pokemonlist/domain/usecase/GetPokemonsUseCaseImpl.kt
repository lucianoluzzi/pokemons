package com.lucianoluzzi.pokemons.pokemonlist.domain.usecase

import com.lucianoluzzi.pokemons.pokemonlist.data.PokemonListRepository

class GetPokemonsUseCaseImpl(private val repository: PokemonListRepository) : GetPokemonsUseCase {

    override suspend fun invoke(): List<PokemonsQuery.Pokemon?> {
        val repositoryResponse = repository.fetchPokemons()
        return repositoryResponse.orEmpty()
    }
}