package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lucianoluzzi.pokemons.databinding.FragmentPokemonListBinding
import com.lucianoluzzi.pokemons.pokemonlist.ui.adapter.PokemonAdapter
import com.lucianoluzzi.pokemons.pokemonlist.ui.adapter.PokemonEntryUIModel
import com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel.ListResponseState
import com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel.PokemonListViewModel
import com.lucianoluzzi.utils.ui.hide
import com.lucianoluzzi.utils.ui.show

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
            when (it) {
                is ListResponseState.Success -> showList(it.value)
                is ListResponseState.Error -> showError(it.error)
            }
        })

        return binding.root
    }

    private fun showList(list: List<PokemonEntryUIModel>) {
        with(binding) {
            progress.hide()
            error.hide()
            pokemons.show()

            pokemonAdapter.submitList(list)
        }
    }

    private fun showError(errorMessage: String?) {
        errorMessage?.let { message ->
            with(binding) {
                progress.hide()
                error.show()
                error.text = message
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemons.adapter = pokemonAdapter
    }
}