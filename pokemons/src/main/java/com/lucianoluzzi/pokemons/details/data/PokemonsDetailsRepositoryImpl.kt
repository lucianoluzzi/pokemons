package com.lucianoluzzi.pokemons.details.data

import PokemonQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonsDetailsRepositoryImpl(
    private val networkClient: ApolloClient,
    private val pokemonQuery: PokemonQuery
) : PokemonDetailsRepository {

    @Throws(Exception::class)
    override suspend fun fetchDetails(): PokemonQuery.Data? = withContext(Dispatchers.IO) {
        val response = networkClient.query(pokemonQuery).toDeferred().await()
        return@withContext response.data
    }
}