package com.lucianoluzzi.pokemons.domain.usecase

import com.lucianoluzzi.pokemons.data.PokemonListRepository

class GetPokemonsUseCaseImpl(private val repository: PokemonListRepository) : GetPokemonsUseCase {

    override suspend fun invoke(): List<PokemonsQuery.Pokemon?> {
        val repositoryResponse = repository.fetchPokemons()
        return repositoryResponse.orEmpty()
    }
}