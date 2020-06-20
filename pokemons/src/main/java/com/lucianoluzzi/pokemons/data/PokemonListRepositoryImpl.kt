package com.lucianoluzzi.pokemons.data

import PokemonsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonListRepositoryImpl(
    private val networkClient: ApolloClient,
    private val pokemonsQuery: PokemonsQuery
) : PokemonListRepository {

    override suspend fun fetchPokemons(): List<PokemonsQuery.Pokemon?>? = withContext(Dispatchers.IO) {
        val response = networkClient.query(pokemonsQuery).toDeferred().await()
        return@withContext response.data?.pokemons
    }
}