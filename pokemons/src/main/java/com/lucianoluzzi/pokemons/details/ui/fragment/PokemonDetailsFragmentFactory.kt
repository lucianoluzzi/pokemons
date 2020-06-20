package com.lucianoluzzi.pokemons.details.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class PokemonDetailsFragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PokemonDetailsFragment::class.java.name -> PokemonDetailsFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}