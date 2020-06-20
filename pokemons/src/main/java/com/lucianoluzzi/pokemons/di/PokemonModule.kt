package com.lucianoluzzi.pokemons.di

import PokemonsQuery
import com.apollographql.apollo.ApolloClient
import com.lucianoluzzi.pokemons.data.PokemonListRepositoryImpl
import com.lucianoluzzi.pokemons.domain.usecase.GetPokemonsUseCase
import com.lucianoluzzi.pokemons.domain.usecase.GetPokemonsUseCaseImpl
import com.lucianoluzzi.pokemons.ui.fragment.PokemonListFragmentFactory
import com.lucianoluzzi.pokemons.ui.viewmodel.PokemonListViewModel
import org.koin.dsl.module

object PokemonModule {
    val module = module {
        factory<GetPokemonsUseCase> {
            val repository = PokemonListRepositoryImpl(
                get() as ApolloClient,
                get() as PokemonsQuery
            )
            GetPokemonsUseCaseImpl(repository)
        }

        factory {
            val viewModel = PokemonListViewModel(get() as GetPokemonsUseCase)
            PokemonListFragmentFactory(
                viewModel
            )
        }
    }
}