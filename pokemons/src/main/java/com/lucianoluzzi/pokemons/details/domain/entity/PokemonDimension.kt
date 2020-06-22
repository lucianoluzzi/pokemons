package com.lucianoluzzi.pokemons.details.domain.entity

data class PokemonDimension(
    val from: String,
    val to: String) {

    val description = "$from - $to"
    val contentDescription = "from ${from.getUnitDescription()} to ${to.getUnitDescription()}"

    private fun String.getUnitDescription(): String {
        return replace("m", " meter")
            .replace("kg", " kilogram")
    }
}