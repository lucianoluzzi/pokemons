package com.lucianoluzzi.pokemons.di

import PokemonQuery
import PokemonsQuery
import com.apollographql.apollo.ApolloClient
import com.lucianoluzzi.pokemons.PokemonFragmentFactory
import com.lucianoluzzi.pokemons.details.data.PokemonsDetailsRepositoryImpl
import com.lucianoluzzi.pokemons.details.domain.usecase.GetPokemonDetailsUseCase
import com.lucianoluzzi.pokemons.details.domain.usecase.GetPokemonDetailsUseCaseImpl
import com.lucianoluzzi.pokemons.details.ui.viewmodel.PokemonDetailsViewModel
import com.lucianoluzzi.pokemons.pokemonlist.data.PokemonListRepositoryImpl
import com.lucianoluzzi.pokemons.pokemonlist.domain.usecase.GetPokemonsUseCase
import com.lucianoluzzi.pokemons.pokemonlist.domain.usecase.GetPokemonsUseCaseImpl
import com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel.PokemonListViewModel
import org.koin.dsl.module

object PokemonModule {
    val module = module {
        factory<GetPokemonsUseCase> {
            val repository = PokemonListRepositoryImpl(
                get() as ApolloClient,
                get() as PokemonsQuery.Builder
            )
            GetPokemonsUseCaseImpl(repository)
        }

        factory<GetPokemonDetailsUseCase> {
            val repository = PokemonsDetailsRepositoryImpl(
                get() as ApolloClient,
                get() as PokemonQuery.Builder
            )
            GetPokemonDetailsUseCaseImpl(
                repository
            )
        }

        factory {
            val detailsViewModel = PokemonDetailsViewModel(get() as GetPokemonDetailsUseCase)
            val listViewModel = PokemonListViewModel(get() as GetPokemonsUseCase)
            PokemonFragmentFactory(listViewModel, detailsViewModel)
        }
    }
}