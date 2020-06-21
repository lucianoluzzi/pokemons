package com.lucianoluzzi.pokemons.pokemonlist.domain.usecase

import PokemonsQuery
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.pokemons.pokemonlist.data.PokemonListRepository
import com.lucianoluzzi.pokemons.pokemonlist.domain.entity.GetPokemonsResultWrapper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetPokemonsUseCaseImplTest {
    private val repository = mock<PokemonListRepository>()
    private val useCase = GetPokemonsUseCaseImpl(repository)

    @Test
    fun `assert useCase returns Data when repository succeed`() = runBlockingTest {
        val response = getMockedResponse()

        whenever(repository.fetchPokemons()).thenReturn(response)
        val result = useCase.invoke()

        assertThat(result is GetPokemonsResultWrapper.Data).isTrue()
        assertThat((result as GetPokemonsResultWrapper.Data).data).isEqualTo(response)
    }

    @Test
    fun `assert useCase returns empty when repository returns null`() = runBlockingTest {
        whenever(repository.fetchPokemons()).thenReturn(null)
        val result = useCase.invoke()

        assertThat(result is GetPokemonsResultWrapper.Data).isTrue()
        assertThat((result as GetPokemonsResultWrapper.Data).data).isEmpty()
    }

    @Test
    fun `assert useCase returns Error when repository throws exception`() = runBlockingTest {
        whenever(repository.fetchPokemons()).thenThrow()
        val result = useCase.invoke()

        assertThat(result is GetPokemonsResultWrapper.Error).isTrue()
        assertThat((result as GetPokemonsResultWrapper.Error).error).isEqualTo("Bzzzt! You must have a POKÉMON to use this!")
    }

    private fun getMockedResponse(): List<PokemonsQuery.Pokemon?>? {
        return listOf(
            PokemonsQuery.Pokemon("", "001", "Luciano", listOf(), ""),
            PokemonsQuery.Pokemon("", "001", "Luciano", listOf(), ""),
            PokemonsQuery.Pokemon("", "001", "Luciano", listOf(), "")
        )
    }
}