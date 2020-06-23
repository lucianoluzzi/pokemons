package com.lucianoluzzi.pokemons.details.data

import PokemonQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.InstantExecutorExtension
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Answers


@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class PokemonsDetailsRepositoryImplTest {

    private val networkClient = mock<ApolloClient>(defaultAnswer = Answers.RETURNS_DEEP_STUBS)
    private val pokemonQueryBuilder =
        mock<PokemonQuery.Builder>(defaultAnswer = Answers.RETURNS_DEEP_STUBS)
    private val repository = PokemonsDetailsRepositoryImpl(networkClient, pokemonQueryBuilder)

    @Test
    fun `assert query value returned when fetchDetails`() = runBlockingTest {
        val mockedQuery = mock<PokemonQuery>()
        val mockedData = mock<PokemonQuery.Data>()
        val mockedResponse = mock<Response<PokemonQuery.Data>>()

        whenever(pokemonQueryBuilder.name("").build()).thenReturn(mockedQuery)
        whenever(networkClient.query(mockedQuery).toDeferred().await()).thenReturn(mockedResponse)
        whenever(mockedResponse.data).thenReturn(mockedData)

        val returnedData = repository.fetchDetails("")
        assertThat(returnedData).isEqualTo(mockedData)
    }
}