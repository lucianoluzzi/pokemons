package com.lucianoluzzi.pokemons.details.domain.usecase

import PokemonQuery
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.pokemons.details.data.PokemonDetailsRepository
import com.lucianoluzzi.pokemons.details.domain.entity.GetDetailsResultWrapper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class GetPokemonDetailsUseCaseImplTest {
    private val repository = mock<PokemonDetailsRepository>()
    private val useCase = GetPokemonDetailsUseCaseImpl(repository)

    @Test
    fun `assert useCase returns repository data as Success`() = runBlockingTest {
        val mockedData = mock<PokemonQuery.Data>()

        whenever(repository.fetchDetails("pokemon")).thenReturn(mockedData)

        val useCaseResponse = useCase.invoke("pokemon")
        assertThat(useCaseResponse is GetDetailsResultWrapper.Success).isTrue()
        assertThat((useCaseResponse as GetDetailsResultWrapper.Success).data).isEqualTo(mockedData)
    }

    @Test
    fun `assert useCase returns repository data as Error when data is null`() = runBlockingTest {
        whenever(repository.fetchDetails("pokemon")).thenReturn(null)

        val useCaseResponse = useCase.invoke("pokemon")
        assertThat(useCaseResponse is GetDetailsResultWrapper.Error).isTrue()
        assertThat((useCaseResponse as GetDetailsResultWrapper.Error).error).isEqualTo("Wild MISSINGNO. appeared!")
    }

    @Test
    fun `assert useCase returns repository data as Error when repository throws`() =
        runBlockingTest {
            whenever(repository.fetchDetails("pokemon")).thenThrow(Exception())

            val useCaseResponse = useCase.invoke("pokemon")
            assertThat(useCaseResponse is GetDetailsResultWrapper.Error).isTrue()
            assertThat((useCaseResponse as GetDetailsResultWrapper.Error).error).isEqualTo("Wild MISSINGNO. appeared!")
        }
}