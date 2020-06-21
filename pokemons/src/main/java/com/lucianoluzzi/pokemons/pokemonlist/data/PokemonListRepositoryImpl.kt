package com.lucianoluzzi.pokemons.pokemonlist.data

import PokemonsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonListRepositoryImpl(
    private val networkClient: ApolloClient,
    private val pokemonsQuery: PokemonsQuery.Builder
) : PokemonListRepository {

    @Throws(Exception::class)
    override suspend fun fetchPokemons(): List<PokemonsQuery.Pokemon?>? = withContext(Dispatchers.IO) {
        val query = pokemonsQuery.pageSize(152).build()
        val response = networkClient.query(query).toDeferred().await()
        return@withContext response.data?.pokemons()
    }
}