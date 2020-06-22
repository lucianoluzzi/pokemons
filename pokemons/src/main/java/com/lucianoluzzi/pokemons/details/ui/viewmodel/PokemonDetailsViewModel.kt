package com.lucianoluzzi.pokemons.details.ui.viewmodel

import PokemonQuery
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.pokemons.details.domain.usecase.GetPokemonDetailsUseCase
import com.lucianoluzzi.pokemons.details.domain.entity.Evolution
import com.lucianoluzzi.pokemons.details.domain.entity.GetDetailsResultWrapper
import com.lucianoluzzi.pokemons.details.domain.entity.PokemonDimension
import com.lucianoluzzi.pokemons.details.domain.entity.Type
import com.lucianoluzzi.pokemons.details.ui.PokemonDetailsUIModel
import com.lucianoluzzi.utils.coroutines.SingleLiveEvent
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {

    val pokemonDetails = SingleLiveEvent<DetailsResponseState>()

    fun fetchPokemonDetails(pokemonName: String) {
        viewModelScope.launch {
            when (val result = getPokemonDetailsUseCase.invoke(pokemonName)) {
                is GetDetailsResultWrapper.Success -> exposeSuccessData(result)
                is GetDetailsResultWrapper.Error -> exposeErrorData(result)
            }
        }
    }

    private fun exposeSuccessData(result: GetDetailsResultWrapper.Success) {
        val uiModel = getUIModel(result.data)
        pokemonDetails.value = DetailsResponseState.Success(uiModel)
    }

    private fun exposeErrorData(result: GetDetailsResultWrapper.Error) {
        val error = result.error.orEmpty()
        pokemonDetails.value = DetailsResponseState.Error(error)
    }

    private fun getUIModel(pokemon: PokemonQuery.Data): PokemonDetailsUIModel {
        return with (pokemon) {
            PokemonDetailsUIModel(
                number = pokemon()?.number().orEmpty(),
                name = pokemon()?.name().orEmpty(),
                classification = pokemon()?.classification().orEmpty(),
                image = pokemon()?.image().orEmpty(),
                types = pokemon()?.types()?.map {
                    Type(it)
                }.orEmpty(),
                resistances = pokemon()?.resistant()?.map {
                    Type(it)
                }.orEmpty(),
                weaknesses = pokemon()?.weaknesses()?.map {
                    Type(it)
                }.orEmpty(),
                evolutions = pokemon()?.evolutions()?.map {
                    Evolution(
                        name = it.name().orEmpty(),
                        image = it.image().orEmpty()
                    )
                }.orEmpty(),
                height = PokemonDimension(
                    pokemon()?.height()?.minimum().orEmpty(),
                    pokemon()?.height()?.maximum().orEmpty()
                ),
                weight = PokemonDimension(
                    pokemon()?.weight()?.minimum().orEmpty(),
                    pokemon()?.weight()?.maximum().orEmpty()
                )
            )
        }
    }
}