package com.lucianoluzzi.pokemons.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lucianoluzzi.pokemons.entities.PokemonEntry

class PokemonEntryDiffCallback : DiffUtil.ItemCallback<PokemonEntry>() {
    override fun areItemsTheSame(oldItem: PokemonEntry, newItem: PokemonEntry): Boolean =
        oldItem.number == newItem.number

    override fun areContentsTheSame(oldItem: PokemonEntry, newItem: PokemonEntry): Boolean =
        oldItem == newItem
}