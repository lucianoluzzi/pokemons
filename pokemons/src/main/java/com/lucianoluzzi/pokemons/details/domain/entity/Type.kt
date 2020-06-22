package com.lucianoluzzi.pokemons.details.domain.entity

import com.lucianoluzzi.pokemons.R

data class Type(val description: String) {
    val typeIcon = when (description) {
        "Flying" -> R.drawable.ic_flying
        "Fire" -> R.drawable.ic_fire
        "Fairy" -> R.drawable.ic_fairy
        "Fighting" -> R.drawable.ic_fighting
        "Dragon" -> R.drawable.ic_dragon
        "Bug" -> R.drawable.ic_bug
        "Eletric" -> R.drawable.ic_electric
        "Ghost" -> R.drawable.ic_ghost
        "Grass" -> R.drawable.ic_grass
        "Normal" -> R.drawable.ic_normal
        "Psychic" -> R.drawable.ic_psychic
        "Water" -> R.drawable.ic_water
        "Ground" -> R.drawable.ic_ground
        "Poison" -> R.drawable.ic_poison
        "Steel" -> R.drawable.ic_steel
        "Ice" -> R.drawable.ic_ice
        "Rock" -> R.drawable.ic_rock
        else -> R.drawable.ic_normal
    }
}