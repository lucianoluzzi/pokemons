package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.robot

import com.lucianoluzzi.pokemons.R
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.hasSize
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.isDisplayed
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.withTextValue

class ListScreenRobot {

    fun withSize(size: Int) {
        R.id.pokemons.hasSize(size)
    }

    fun withProgress() {
        R.id.progress.isDisplayed()
    }

    fun withError(error: String) {
        R.id.error.withTextValue(error)
    }
}