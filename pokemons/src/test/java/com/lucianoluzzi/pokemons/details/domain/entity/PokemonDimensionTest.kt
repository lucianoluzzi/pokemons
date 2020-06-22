package com.lucianoluzzi.pokemons.details.domain.entity

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class PokemonDimensionTest {

    @Test
    fun `assert description combines minimum and maximum values`() {
        val minimumAmount = "10kg"
        val maximumAmount = "20.1kg"
        val weightDimension = PokemonDimension(minimumAmount, maximumAmount)

        val expectedDescription = "$minimumAmount - $maximumAmount"
        val actualDescription = weightDimension.description
        assertThat(actualDescription).isEqualTo(expectedDescription)
    }

    @Test
    fun `assert KG units are described as kilogram to content description`() {
        val minimumAmount = "10kg"
        val maximumAmount = "20.1kg"
        val weightDimension = PokemonDimension(minimumAmount, maximumAmount)

        val expectedContentDescription = "from 10 kilogram to 20.1 kilogram"
        val actualContentDescription = weightDimension.contentDescription
        assertThat(actualContentDescription).isEqualTo(expectedContentDescription)
    }

    @Test
    fun `assert M units are described as meter to content description`() {
        val minimumAmount = "0.5m"
        val maximumAmount = "1.75m"
        val heightDimension = PokemonDimension(minimumAmount, maximumAmount)

        val expectedContentDescription = "from 0.5 meter to 1.75 meter"
        val actualContentDescription = heightDimension.contentDescription
        assertThat(actualContentDescription).isEqualTo(expectedContentDescription)
    }
}