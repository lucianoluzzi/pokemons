package com.lucianoluzzi.pokemons.details.ui

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.pokemons.details.domain.entity.PokemonDimension
import org.junit.jupiter.api.Test

class PokemonDetailsUIModelTest {
    private val uiModel = PokemonDetailsUIModel(
        number = "001",
        name = "Pikachu",
        classification = "Mouse pokemon",
        types = listOf(),
        weaknesses = listOf(),
        resistances = listOf(),
        evolutions = listOf(),
        height = PokemonDimension("", ""),
        weight = PokemonDimension("", "")
    )

    @Test
    fun `assert content description is pokemon's number in a descriptive way`() {
        val contentDescription = uiModel.contentDescription
        assertThat(contentDescription).isEqualTo("Pokémon number ${uiModel.number}")
    }
}