package com.lucianoluzzi.pokemons.pokemonlist.domain.usecase

import com.lucianoluzzi.pokemons.pokemonlist.data.PokemonListRepository
import com.lucianoluzzi.pokemons.pokemonlist.domain.entity.GetPokemonsResultWrapper

class GetPokemonsUseCaseImpl(private val repository: PokemonListRepository) : GetPokemonsUseCase {

    override suspend fun invoke(): GetPokemonsResultWrapper {
        return try {
            val repositoryResponse = repository.fetchPokemons()
            GetPokemonsResultWrapper.Data(repositoryResponse.orEmpty())
        } catch (exception: Exception) {
            GetPokemonsResultWrapper.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    private companion object {
        const val DEFAULT_ERROR_MESSAGE = "Bzzzt! You must have a POKÉMON to use this!"
    }
}