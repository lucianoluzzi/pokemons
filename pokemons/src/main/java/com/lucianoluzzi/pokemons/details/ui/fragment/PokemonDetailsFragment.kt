package com.lucianoluzzi.pokemons.details.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.api.load
import coil.request.CachePolicy
import com.lucianoluzzi.pokemons.R
import com.lucianoluzzi.pokemons.databinding.FragmentPokemonDetailsBinding
import com.lucianoluzzi.pokemons.details.ui.PokemonDetailsUIModel
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
        setTitle(pokemonName)

        viewModel.fetchPokemonDetails(pokemonName)
    }

    private fun setTitle(pokemonName: String) {
        if (pokemonName.isNotEmpty()) {
            val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
            toolbar.title = pokemonName
        }
    }

    private fun showData(response: DetailsResponseState.Success) {
        with(binding) {
            progress.hide()
            error.hide()
            contentContainer.show()
            binding.pokemon = response.data
            loadImage(response.data)
        }
    }

    private fun loadImage(pokemon: PokemonDetailsUIModel) {
        pokemon.image?.let {
            binding.image.load(it) {
                memoryCachePolicy(CachePolicy.DISABLED)
                crossfade(true)
                placeholder(R.mipmap.ic_pokeball_foreground)
            }
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