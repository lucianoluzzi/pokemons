package com.lucianoluzzi.pokemons.pokemonlist.ui.adapter

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class PokemonEntryUIModelTest {
    private val uiModel = PokemonEntryUIModel(
        number = "001",
        name = "Bulbasaur",
        image = "www.google.com"
    )

    @Test
    fun `assert content description is described in a descriptive way`() {
        val expectedContentDescription = "Pokémon number ${uiModel.number}, ${uiModel.name}"
        assertThat(uiModel.contentDescription).isEqualTo(expectedContentDescription)
    }
}