package com.lucianoluzzi.pokemons.pokemonlist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.lucianoluzzi.pokemons.R
import com.lucianoluzzi.pokemons.databinding.ListItemPokemonBinding

class PokemonAdapter(
    private val clickListener: (pokemon: PokemonEntryUIModel) -> Unit
) : ListAdapter<PokemonEntryUIModel, PokemonAdapter.PokemonViewHolder>(
    PokemonEntryDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPokemonBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(
            binding,
            clickListener
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonEntry = getItem(position)
        holder.bind(pokemonEntry)
    }

    class PokemonViewHolder(
        private val itemBinding: ListItemPokemonBinding,
        private val clickListener: (pokemon: PokemonEntryUIModel) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(pokemon: PokemonEntryUIModel?) {
            pokemon?.let { pokemonEntry ->
                itemBinding.root.setOnClickListener {
                    clickListener(pokemonEntry)
                }
                itemBinding.image.load(pokemonEntry.image) {
                    crossfade(true)
                    placeholder(R.mipmap.ic_pokeball_foreground)
                }
                itemBinding.root.contentDescription = pokemonEntry.contentDescription
                itemBinding.pokemon = pokemonEntry
            }
        }
    }
}