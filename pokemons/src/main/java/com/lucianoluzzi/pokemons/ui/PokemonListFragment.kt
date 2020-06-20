package com.lucianoluzzi.pokemons.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucianoluzzi.pokemons.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment() {
    private val binding by lazy {
        val inflater = LayoutInflater.from(requireContext())
        FragmentPokemonListBinding.inflate(inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}