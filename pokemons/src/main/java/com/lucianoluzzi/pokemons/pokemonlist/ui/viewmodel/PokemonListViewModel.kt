package com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel

import PokemonsQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.pokemons.pokemonlist.domain.usecase.GetPokemonsUseCase
import com.lucianoluzzi.pokemons.pokemonlist.ui.adapter.PokemonEntryUIModel
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val mPokemons = MutableLiveData<List<PokemonEntryUIModel>>()
    val pokemons: LiveData<List<PokemonEntryUIModel>> = mPokemons

    init {
        viewModelScope.launch {
            val pokemonsResponse = getPokemonsUseCase.invoke()
            mPokemons.value = transformIntoUIModel(pokemonsResponse)
        }
    }

    private fun transformIntoUIModel(entityList: List<PokemonsQuery.Pokemon?>): List<PokemonEntryUIModel> =
        entityList.map {
            PokemonEntryUIModel(
                it?.number.orEmpty(),
                it?.name.orEmpty()
            )
        }
}