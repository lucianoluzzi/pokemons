package com.lucianoluzzi.pokemons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucianoluzzi.pokemons.databinding.ListItemPokemonBinding

class PokemonAdapter : ListAdapter<PokemonEntryUIModel, PokemonAdapter.PokemonViewHolder>(
    PokemonEntryDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPokemonBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonEntry = getItem(position)
        holder.bind(pokemonEntry)
    }

    class PokemonViewHolder(
        private val itemBinding: ListItemPokemonBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(pokemon: PokemonEntryUIModel?) {
            itemBinding.pokemon = pokemon
        }
    }
}