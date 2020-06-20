package com.lucianoluzzi.network.di

import PokemonsQuery
import com.apollographql.apollo.ApolloClient
import com.lucianoluzzi.network.NetworkClientProvider
import org.koin.dsl.module
import java.util.Collections.singleton

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