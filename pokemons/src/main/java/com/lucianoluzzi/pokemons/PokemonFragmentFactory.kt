package com.lucianoluzzi.pokemons

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lucianoluzzi.pokemons.details.ui.fragment.PokemonDetailsFragment
import com.lucianoluzzi.pokemons.details.ui.viewmodel.PokemonDetailsViewModel
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.PokemonListFragment
import com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel.PokemonListViewModel

class PokemonFragmentFactory(
    private val listViewModel: PokemonListViewModel,
    private val detailsViewModel: PokemonDetailsViewModel
) :
    FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PokemonDetailsFragment::class.java.name -> PokemonDetailsFragment(
                detailsViewModel
            )
            PokemonListFragment::class.java.name -> PokemonListFragment(listViewModel)
            else -> super.instantiate(classLoader, className)
        }
    }
}