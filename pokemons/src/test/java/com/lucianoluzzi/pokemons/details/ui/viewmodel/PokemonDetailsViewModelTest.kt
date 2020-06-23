package com.lucianoluzzi.pokemons.details.ui.viewmodel

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.InstantExecutorExtension
import com.lucianoluzzi.pokemons.details.domain.entity.Evolution
import com.lucianoluzzi.pokemons.details.domain.entity.GetDetailsResultWrapper
import com.lucianoluzzi.pokemons.details.domain.entity.PokemonDimension
import com.lucianoluzzi.pokemons.details.domain.entity.Type
import com.lucianoluzzi.pokemons.details.domain.usecase.GetPokemonDetailsUseCase
import com.lucianoluzzi.pokemons.details.ui.PokemonDetailsUIModel
import com.lucianoluzzi.pokemons.pokemonlist.data.CoroutineScopeExtension
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, CoroutineScopeExtension::class)
class PokemonDetailsViewModelTest {
    private val useCase = mock<GetPokemonDetailsUseCase>()
    private val viewModel = PokemonDetailsViewModel(useCase)

    @Test
    fun `assert liveData exposes useCase data as Success State`() = runBlockingTest {
        val mockPokemon = getPokemon()
        val mockData = mock<PokemonQuery.Data> {
            on { pokemon() } doReturn mockPokemon
        }
        val mockResponse = GetDetailsResultWrapper.Success(mockData)

        whenever(useCase.invoke("pokemon")).thenReturn(mockResponse)
        viewModel.fetchPokemonDetails("pokemon")

        assertThat(viewModel.pokemonDetails.value is DetailsResponseState.Success).isTrue()
        val success = viewModel.pokemonDetails.value as DetailsResponseState.Success
        assertThat(success.data).isEqualTo(getUIModel(mockPokemon))
    }

    @Test
    fun `assert liveData exposes useCase data as Error State`() = runBlockingTest {
        val error = "Error!"
        val mockResponse = GetDetailsResultWrapper.Error(error)

        whenever(useCase.invoke("pokemon")).thenReturn(mockResponse)
        viewModel.fetchPokemonDetails("pokemon")

        assertThat(viewModel.pokemonDetails.value is DetailsResponseState.Error).isTrue()
        val errorResponseState = viewModel.pokemonDetails.value as DetailsResponseState.Error
        assertThat(errorResponseState.error).isEqualTo(error)
    }

    private fun getPokemon(): PokemonQuery.Pokemon {
        return mock {
            on { name() } doReturn "Bulbasaur"
            on { image() } doReturn "http://google.com"
            on { number() } doReturn "001"
            on { classification() } doReturn "001"
            on { weaknesses() } doReturn listOf()
            on { resistant() } doReturn listOf()
            on { evolutions() } doReturn listOf()
            on { height() } doReturn PokemonQuery.Height("", "1m", "2m")
            on { weight() } doReturn PokemonQuery.Weight("", "1kg", "2kg")
        }
    }

    private fun getUIModel(pokemon: PokemonQuery.Pokemon): PokemonDetailsUIModel {
        return with (pokemon) {
            PokemonDetailsUIModel(
                number = number().orEmpty(),
                name = name().orEmpty(),
                classification = classification().orEmpty(),
                image = image().orEmpty(),
                types = types()?.map {
                    Type(it)
                }.orEmpty(),
                resistances = resistant()?.map {
                    Type(it)
                }.orEmpty(),
                weaknesses = weaknesses()?.map {
                    Type(it)
                }.orEmpty(),
                evolutions = evolutions()?.map {
                    Evolution(
                        name = it.name().orEmpty(),
                        image = it.image().orEmpty()
                    )
                }.orEmpty(),
                height = PokemonDimension(
                    height()?.minimum().orEmpty(),
                    height()?.maximum().orEmpty()
                ),
                weight = PokemonDimension(
                    weight()?.minimum().orEmpty(),
                    weight()?.maximum().orEmpty()
                )
            )
        }
    }
}