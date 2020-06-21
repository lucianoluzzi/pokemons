package com.lucianoluzzi.network.data

import com.apollographql.apollo.ApolloClient
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class NetworkClientProviderTest {

    private val clientProvider = NetworkClientProvider()

    @Test
    fun `assert apollo client instance`() {
        val apolloClient = clientProvider.apolloClient

        assertThat(apolloClient).isInstanceOf(ApolloClient::class.java)
    }

    @Test
    fun `assert client base url`() {
        val apolloClient = clientProvider.apolloClient
        assertThat(apolloClient.serverUrl.url().toString()).isEqualTo("https://graphql-pokemon.now.sh/")
    }
}