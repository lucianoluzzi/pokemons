package com.lucianoluzzi.pokemons.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lucianoluzzi.pokemons.databinding.FragmentPokemonListBinding
import com.lucianoluzzi.pokemons.ui.adapter.PokemonAdapter
import com.lucianoluzzi.pokemons.ui.viewmodel.PokemonListViewModel

class PokemonListFragment(private val viewModel: PokemonListViewModel) : Fragment() {

    private val binding by lazy {
        val inflater = LayoutInflater.from(requireContext())
        FragmentPokemonListBinding.inflate(inflater)
    }

    private val pokemonAdapter = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
            pokemonAdapter.submitList(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemons.adapter = pokemonAdapter
    }
}