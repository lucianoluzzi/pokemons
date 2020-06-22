package com.lucianoluzzi.pokemons.details.domain.usecase

import com.lucianoluzzi.pokemons.details.data.PokemonDetailsRepository
import com.lucianoluzzi.pokemons.details.domain.entity.GetDetailsResultWrapper

class GetPokemonDetailsUseCaseImpl(private val repository: PokemonDetailsRepository) :
    GetPokemonDetailsUseCase {

    override suspend fun invoke(pokemonName: String): GetDetailsResultWrapper {
        return try {
            val response = repository.fetchDetails(pokemonName)
            response?.let {
                GetDetailsResultWrapper.Success(it)
            } ?: run {
                returnError()
            }
        } catch (exception: Exception) {
            returnError()
        }
    }

    private fun returnError() = GetDetailsResultWrapper.Error(DEFAULT_GET_DETAILS_ERROR)

    private companion object {
        const val DEFAULT_GET_DETAILS_ERROR = ""
    }
}