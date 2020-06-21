package com.lucianoluzzi.pokemons.details.ui.viewmodel

import PokemonQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.pokemons.details.domain.GetPokemonDetailsUseCase
import com.lucianoluzzi.pokemons.details.domain.entity.GetDetailsResultWrapper
import com.lucianoluzzi.pokemons.details.ui.PokemonDetailsUIModel
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {

    private val mPokemonDetails = MutableLiveData<DetailsResponseState>().apply {
        value = DetailsResponseState.Loading
    }
    val pokemonDetails: LiveData<DetailsResponseState> = mPokemonDetails

    fun fetchPokemonDetails(pokemonName: String) {
        viewModelScope.launch {
            viewModelScope.launch {
                when (val result = getPokemonDetailsUseCase.invoke(pokemonName)) {
                    is GetDetailsResultWrapper.Success -> exposeSuccessData(result)
                    is GetDetailsResultWrapper.Error -> exposeErrorData(result)
                }
            }
        }
    }

    private fun exposeSuccessData(result: GetDetailsResultWrapper.Success) {
        val uiModel = getUIModel(result.data)
        mPokemonDetails.value = DetailsResponseState.Success(uiModel)
    }

    private fun exposeErrorData(result: GetDetailsResultWrapper.Error) {
        val error = result.error.orEmpty()
        mPokemonDetails.value = DetailsResponseState.Error(error)
    }

    private fun getUIModel(pokemon: PokemonQuery.Data): PokemonDetailsUIModel {
        return with (pokemon) {
            PokemonDetailsUIModel (
                pokemon()?.number().orEmpty(),
                pokemon()?.name().orEmpty(),
                pokemon()?.classification().orEmpty(),
                pokemon()?.image().orEmpty()
            )
        }
    }
}