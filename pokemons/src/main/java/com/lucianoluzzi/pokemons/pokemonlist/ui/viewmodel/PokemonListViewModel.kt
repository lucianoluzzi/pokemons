package com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel

import PokemonsQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.pokemons.pokemonlist.domain.entity.GetPokemonsResultWrapper
import com.lucianoluzzi.pokemons.pokemonlist.domain.usecase.GetPokemonsUseCase
import com.lucianoluzzi.pokemons.pokemonlist.ui.adapter.PokemonEntryUIModel
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val mPokemons = MutableLiveData<ListResponseState>().apply {
        value = ListResponseState.Loading
    }
    val pokemons: LiveData<ListResponseState> = mPokemons

    init {
        viewModelScope.launch {
            val pokemonsResponse = getPokemonsUseCase.invoke()

            mPokemons.value = when (pokemonsResponse) {
                is GetPokemonsResultWrapper.Data -> ListResponseState.Success(
                    transformIntoUIModel(
                        pokemonsResponse.data
                    )
                )
                is GetPokemonsResultWrapper.Error -> ListResponseState.Error(pokemonsResponse.error)
            }
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