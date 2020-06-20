package com.lucianoluzzi.network.di

import PokemonsQuery
import com.apollographql.apollo.ApolloClient
import com.lucianoluzzi.network.data.NetworkClientProvider
import org.koin.dsl.module

object NetworkModule {
    val module = module {
        factory<ApolloClient> {
            NetworkClientProvider().apolloClient
        }

        factory {
            PokemonsQuery(152)
        }
    }
}