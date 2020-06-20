package com.lucianoluzzi.pokemons.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lucianoluzzi.pokemons.ui.PokemonEntryUIModel

class PokemonEntryDiffCallback : DiffUtil.ItemCallback<PokemonEntryUIModel>() {
    override fun areItemsTheSame(
        oldItem: PokemonEntryUIModel,
        newItem: PokemonEntryUIModel
    ): Boolean =
        oldItem.number == newItem.number

    override fun areContentsTheSame(
        oldItem: PokemonEntryUIModel,
        newItem: PokemonEntryUIModel
    ): Boolean =
        oldItem == newItem
}