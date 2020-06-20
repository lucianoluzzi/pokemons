package com.lucianoluzzi.pokedex

import android.app.Application
import com.lucianoluzzi.network.di.NetworkModule
import org.koin.core.context.startKoin

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            val modules = listOf(
                NetworkModule.module
            )

            modules(modules)
        }
    }
}