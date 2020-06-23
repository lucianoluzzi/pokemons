package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.robot

import com.lucianoluzzi.pokemons.R
import com.lucianoluzzi.pokemons.details.domain.entity.PokemonDimension
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.*

class DetailsScreenRobot {

    fun withProgress() {
        R.id.progress.isDisplayed()
    }

    fun withError(error: String) {
        R.id.error.withTextValue(error)
    }

    fun withName(name: String) {
        R.id.name.withTextValue(name)
    }

    fun withNumber(number: String) {
        R.id.number.withTextValue("No. $number")
    }

    fun withClassification(classification: String) {
        R.id.classification.withTextValue(classification)
    }

    fun withHeight(height: PokemonDimension) {
        R.id.height.withTextValue("HT ${height.description}")
    }

    fun withWeight(weight: PokemonDimension) {
        R.id.weight.withTextValue("WT ${weight.description}")
    }

    fun withFirstType() {
        R.id.type_one.isDisplayed()
    }

    fun withSecondType() {
        R.id.type_two.isDisplayed()
    }

    fun withoutSecondType() {
        R.id.type_two.isNotDisplayed()
    }

    fun withWeaknessesSize(size: Int) {
        R.id.weaknesses.hasSize(size)
    }

    fun withResistancesSize(size: Int) {
        R.id.resistances.hasSize(size)
    }

    fun withEvolutionsSize(size: Int) {
        R.id.evolutions.hasSize(size)
    }
}