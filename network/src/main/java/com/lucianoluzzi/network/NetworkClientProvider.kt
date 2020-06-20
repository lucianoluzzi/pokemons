package com.lucianoluzzi.network

import com.apollographql.apollo.ApolloClient

class NetworkClientProvider {
    val apolloClient = ApolloClient.builder()
        .serverUrl(BASE_URl)
        .build()

    private companion object {
        const val BASE_URl = "https://graphql-pokemon.now.sh/"
    }
}