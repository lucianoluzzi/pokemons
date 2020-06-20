package com.lucianoluzzi.pokemons.di

import com.lucianoluzzi.pokemons.ui.PokemonListFragmentFactory
import com.lucianoluzzi.pokemons.ui.viewmodel.PokemonListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PokemonModule {
    val module = module {
        viewModel {
            PokemonListViewModel()
        }

        factory {
            PokemonListFragmentFactory(get() as PokemonListViewModel)
        }
    }
}