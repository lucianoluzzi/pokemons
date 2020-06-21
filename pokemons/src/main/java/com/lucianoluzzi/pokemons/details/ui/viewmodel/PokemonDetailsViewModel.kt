package com.lucianoluzzi.pokemons.details.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.pokemons.details.domain.GetPokemonDetailsUseCase
import com.lucianoluzzi.pokemons.details.domain.entity.GetDetailsResultWrapper
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {
    private val mPokemonDetails = MutableLiveData<GetDetailsUIModel>()
    val pokemonDetails: LiveData<GetDetailsUIModel> = mPokemonDetails

    init {
        mPokemonDetails.value = GetDetailsUIModel.Loading

        viewModelScope.launch {
            when (val result = getPokemonDetailsUseCase.invoke()) {
                is GetDetailsResultWrapper.Success -> mPokemonDetails.value = GetDetailsUIModel.Success(result.data)
                is GetDetailsResultWrapper.Error -> mPokemonDetails.value = GetDetailsUIModel.Error(result.error.orEmpty())
            }
        }
    }
}