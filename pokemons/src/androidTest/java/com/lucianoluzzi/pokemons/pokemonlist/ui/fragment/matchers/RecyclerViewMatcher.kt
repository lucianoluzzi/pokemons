package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object RecyclerViewMatcher {

    fun withListSize(size: Int): Matcher<View> =
        object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                return (view as RecyclerView).adapter?.itemCount == size
            }

            override fun describeTo(description: Description) {
                description.appendText(RecyclerViewMatcher::class.java.simpleName)
            }
        }
}