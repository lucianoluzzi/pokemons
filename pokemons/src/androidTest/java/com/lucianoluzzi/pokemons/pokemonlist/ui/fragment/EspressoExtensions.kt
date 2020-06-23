package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.matchers.DrawableMatcher
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.matchers.RecyclerViewMatcher
import org.hamcrest.CoreMatchers


fun Int.hasSize(size: Int) {
    onView(withId(this))
        .check(matches(RecyclerViewMatcher.withListSize(size)))
}

fun Int.isDisplayed() =
    onView(withId(this)).check(matches(ViewMatchers.isDisplayed()))

fun Int.withTextValue(text: String) =
    onView(withId(this)).check(matches(ViewMatchers.withText(text)))

fun Int.isNotDisplayed(): ViewInteraction =
    onView(withId(this)).check(matches(CoreMatchers.not(ViewMatchers.isDisplayed())))

fun Int.withDrawable(drawableId: Int): ViewInteraction =
    onView(withId(this))
        .check(matches(ViewMatchers.isDisplayed()))
        .check(matches(DrawableMatcher.withDrawable(drawableId)))
