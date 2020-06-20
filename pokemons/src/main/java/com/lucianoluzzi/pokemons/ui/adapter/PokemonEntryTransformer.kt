package com.lucianoluzzi.pokemons.ui.adapter

import com.lucianoluzzi.pokemons.entities.PokemonEntry
import com.lucianoluzzi.utils.Transformer

class PokemonEntryTransformer : Transformer<PokemonEntry, PokemonEntryUIModel> {
    override fun transform(input: PokemonEntry): PokemonEntryUIModel {
        val formattedNumber = String.format("%03d", input.number)
        return PokemonEntryUIModel(formattedNumber, input.name)
    }
}