package com.lucianoluzzi.pokemons.details.domain.entity

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.pokemons.R
import org.junit.jupiter.api.Test

class TypeTest {

    @Test
    fun `assert drawable returned when description is Flying`() {
        val type = Type("Flying")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_flying)
    }

    @Test
    fun `assert drawable returned when description is Fire`() {
        val type = Type("Fire")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_fire)
    }

    @Test
    fun `assert drawable returned when description is Fighting`() {
        val type = Type("Fighting")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_fighting)
    }

    @Test
    fun `assert drawable returned when description is Water`() {
        val type = Type("Water")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_water)
    }

    @Test
    fun `assert drawable returned when description is Ice`() {
        val type = Type("Ice")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_ice)
    }

    @Test
    fun `assert drawable returned when description is Steel`() {
        val type = Type("Steel")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_steel)
    }

    @Test
    fun `assert drawable returned when description is Ghost`() {
        val type = Type("Ghost")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_ghost)
    }

    @Test
    fun `assert drawable returned when description is Psychic`() {
        val type = Type("Psychic")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_psychic)
    }

    @Test
    fun `assert drawable returned when description is Bug`() {
        val type = Type("Bug")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_bug)
    }

    @Test
    fun `assert drawable returned when description is Grass`() {
        val type = Type("Grass")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_grass)
    }

    @Test
    fun `assert drawable returned when description is Normal`() {
        val type = Type("Normal")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_normal)
    }

    @Test
    fun `assert drawable returned when description is Dragon`() {
        val type = Type("Dragon")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_dragon)
    }

    @Test
    fun `assert drawable returned when description is Poison`() {
        val type = Type("Poison")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_poison)
    }

    @Test
    fun `assert drawable returned when description is Fairy`() {
        val type = Type("Fairy")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_fairy)
    }

    @Test
    fun `assert drawable returned when description is Ground`() {
        val type = Type("Ground")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_ground)
    }

    @Test
    fun `assert drawable returned when description is Rock`() {
        val type = Type("Rock")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_rock)
    }

    @Test
    fun `assert drawable returned when description is Electric`() {
        val type = Type("Electric")
        assertThat(type.typeIcon).isEqualTo(R.drawable.ic_electric)
    }
}