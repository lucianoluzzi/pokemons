package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

fun Int.hasSize(size: Int) {
    onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(RecyclerViewMatcher.withListSize(size)))
}