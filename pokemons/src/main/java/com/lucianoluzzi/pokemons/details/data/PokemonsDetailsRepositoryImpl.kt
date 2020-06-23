package com.lucianoluzzi.pokemons.details.data

import PokemonQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.lucianoluzzi.utils.coroutines.DispatcherRegistry
import kotlinx.coroutines.withContext

class PokemonsDetailsRepositoryImpl(
    private val networkClient: ApolloClient,
    private val pokemonQueryBuilder: PokemonQuery.Builder
) : PokemonDetailsRepository {

    @Throws(Exception::class)
    override suspend fun fetchDetails(pokemonName: String): PokemonQuery.Data? =
        withContext(DispatcherRegistry.IO) {
            val query = pokemonQueryBuilder.name(pokemonName).build()
            val response = networkClient.query(query).toDeferred().await()
            return@withContext response.data
        }
}