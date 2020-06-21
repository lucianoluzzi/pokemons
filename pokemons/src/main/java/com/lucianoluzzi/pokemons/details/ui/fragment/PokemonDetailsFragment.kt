package com.lucianoluzzi.pokemons.details.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.lucianoluzzi.pokemons.R
import com.lucianoluzzi.pokemons.databinding.FragmentPokemonDetailsBinding
import com.lucianoluzzi.pokemons.details.ui.viewmodel.DetailsResponseState
import com.lucianoluzzi.pokemons.details.ui.viewmodel.PokemonDetailsViewModel
import com.lucianoluzzi.utils.ui.hide
import com.lucianoluzzi.utils.ui.show

class PokemonDetailsFragment(private val viewModel: PokemonDetailsViewModel) : Fragment() {

    private val binding by lazy {
        val inflater = LayoutInflater.from(requireContext())
        FragmentPokemonDetailsBinding.inflate(inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.pokemonDetails.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DetailsResponseState.Success -> showData(it)
                is DetailsResponseState.Error -> showError(it)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonName = arguments?.getString("pokemon_name_argument").orEmpty()
        viewModel.fetchPokemonDetails(pokemonName)
    }

    private fun showData(response: DetailsResponseState.Success) {
        with(binding) {
            progress.hide()
            error.hide()
            contentContainer.show()
            binding.pokemon = response.data
        }
    }

    private fun showError(response: DetailsResponseState.Error) {
        with(binding) {
            progress.hide()
            contentContainer.hide()
            error.show()
            error.text = response.error
            error.contentDescription = response.error
        }
    }
}