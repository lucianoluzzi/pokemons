package com.lucianoluzzi.pokemons.pokemonlist.ui.adapter

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class PokemonEntryDiffCallbackTest {
    private val diffCallback = PokemonEntryDiffCallback()

    @Test
    fun `assert not the same when different numbers`() {
        val firstPokemon = PokemonEntryUIModel("001", "pikachu", "www")
        val secondPokemon = PokemonEntryUIModel("002", "pikachu", "www")

        assertThat(diffCallback.areItemsTheSame(firstPokemon, secondPokemon)).isFalse()
    }

    @Test
    fun `assert not same when same numbers`() {
        val firstPokemon = PokemonEntryUIModel("001", "pikachu", "www")
        val secondPokemon = PokemonEntryUIModel("001", "pikachu", "www")

        assertThat(diffCallback.areItemsTheSame(firstPokemon, secondPokemon)).isTrue()
    }

    @Test
    fun `assert contents are the same when same`() {
        val firstPokemon = PokemonEntryUIModel("001", "pikachu", "www")
        val secondPokemon = PokemonEntryUIModel("001", "pikachu", "www")

        assertThat(diffCallback.areContentsTheSame(firstPokemon, secondPokemon)).isTrue()
    }

    @Test
    fun `assert contents not the same when different number`() {
        val firstPokemon = PokemonEntryUIModel("001", "pikachu", "www")
        val secondPokemon = PokemonEntryUIModel("002", "pikachu", "www")

        assertThat(diffCallback.areContentsTheSame(firstPokemon, secondPokemon)).isFalse()
    }

    @Test
    fun `assert contents not the same when different name`() {
        val firstPokemon = PokemonEntryUIModel("001", "pikachu", "www")
        val secondPokemon = PokemonEntryUIModel("001", "bulbasaur", "www")

        assertThat(diffCallback.areContentsTheSame(firstPokemon, secondPokemon)).isFalse()
    }

    @Test
    fun `assert contents not the same when different image`() {
        val firstPokemon = PokemonEntryUIModel("001", "pikachu", "www")
        val secondPokemon = PokemonEntryUIModel("001", "pikachu", "ww")

        assertThat(diffCallback.areContentsTheSame(firstPokemon, secondPokemon)).isFalse()
    }
}