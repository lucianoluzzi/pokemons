package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel.PokemonListViewModel

class PokemonListFragmentFactory(private val viewModel: PokemonListViewModel) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PokemonListFragment::class.java.name -> PokemonListFragment(
                viewModel
            )
            else -> super.instantiate(classLoader, className)
        }
    }
}