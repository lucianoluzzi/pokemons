package com.lucianoluzzi.network.di

import com.lucianoluzzi.network.NetworkClientProvider
import org.koin.dsl.module

object NetworkModule {
    val module = module {
        NetworkClientProvider()
    }
}