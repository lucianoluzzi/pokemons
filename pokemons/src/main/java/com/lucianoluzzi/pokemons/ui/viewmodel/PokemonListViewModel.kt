package com.lucianoluzzi.pokemons.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucianoluzzi.pokemons.entities.PokemonEntry
import com.lucianoluzzi.pokemons.ui.adapter.PokemonEntryTransformer
import com.lucianoluzzi.pokemons.ui.adapter.PokemonEntryUIModel

class PokemonListViewModel : ViewModel() {
    private val mPokemons = MutableLiveData<List<PokemonEntryUIModel>>().apply {
        val response = listOf(
            PokemonEntry(1, "Bulbasaur")
        )
        val transformer = PokemonEntryTransformer()
        val uiModelList = mutableListOf<PokemonEntryUIModel>()
        response.forEach {
            uiModelList.add(transformer.transform(it))
        }

        value = uiModelList
    }
    val pokemons: LiveData<List<PokemonEntryUIModel>> = mPokemons
}