package com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel

import PokemonsQuery
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.InstantExecutorExtension
import com.lucianoluzzi.pokemons.pokemonlist.data.CoroutineScopeExtension
import com.lucianoluzzi.pokemons.pokemonlist.domain.entity.GetPokemonsResultWrapper
import com.lucianoluzzi.pokemons.pokemonlist.domain.usecase.GetPokemonsUseCase
import com.lucianoluzzi.pokemons.pokemonlist.ui.adapter.PokemonEntryUIModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, CoroutineScopeExtension::class)
class PokemonListViewModelTest {
    private val useCase = mock<GetPokemonsUseCase>()

    @Test
    fun `assert exposes response state succes when useCase returns success`() = runBlockingTest {
        val mockedPockemonList = getMockedPockemonList()
        val mockUseCaseResponse = mock<GetPokemonsResultWrapper.Data> {
            on { data } doReturn mockedPockemonList
        }

        whenever(useCase.invoke()).thenReturn(mockUseCaseResponse)
        val viewModel = PokemonListViewModel(useCase)

        assertThat(viewModel.pokemons.value is ListResponseState.Success).isTrue()
        val success = viewModel.pokemons.value as ListResponseState.Success
        assertThat(success.value).isEqualTo(getUIModelList(mockedPockemonList))
    }

    @Test
    fun `assert exposes response state error when useCase returns error`() = runBlockingTest {
        val error = "Error"
        val mockUseCaseResponse = GetPokemonsResultWrapper.Error(error)

        whenever(useCase.invoke()).thenReturn(mockUseCaseResponse)
        val viewModel = PokemonListViewModel(useCase)

        assertThat(viewModel.pokemons.value is ListResponseState.Error).isTrue()
        val errorResponse = viewModel.pokemons.value as ListResponseState.Error
        assertThat(errorResponse.error).isEqualTo(error)
    }

    private fun getMockedPockemonList(): List<PokemonsQuery.Pokemon?> {
        return listOf(
            PokemonsQuery.Pokemon("", "001", "zero zero one", listOf(), ""),
            PokemonsQuery.Pokemon("", "002", "zero zero two", listOf(), "")
        )
    }

    private fun getUIModelList(pokemons: List<PokemonsQuery.Pokemon?>): List<PokemonEntryUIModel> {
        return pokemons.map {
            PokemonEntryUIModel(it?.number().orEmpty(), it?.name().orEmpty(), it?.image().orEmpty())
        }
    }
}