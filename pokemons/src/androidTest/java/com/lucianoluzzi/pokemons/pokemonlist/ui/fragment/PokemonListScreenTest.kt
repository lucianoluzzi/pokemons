package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import com.lucianoluzzi.pokemons.PokemonFragmentFactory
import com.lucianoluzzi.pokemons.R
import com.lucianoluzzi.pokemons.details.ui.viewmodel.PokemonDetailsViewModel
import com.lucianoluzzi.pokemons.pokemonlist.ui.adapter.PokemonEntryUIModel
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.robot.ListScreenRobot
import com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel.ListResponseState
import com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel.PokemonListViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test

class PokemonListScreenTest {
    private val liveData = MutableLiveData<ListResponseState>()
    private val listViewModel = mock<PokemonListViewModel> {
        on { pokemons } doReturn liveData
    }
    private val detailsViewModel = mock<PokemonDetailsViewModel>()

    @Before
    fun setUp() {
        launchFragment()
    }

    @Test
    fun checkListShownWhenSuccess() {
        val successState = ListResponseState.Success(
            listOf(
                PokemonEntryUIModel("001", "Bulbasaur", ""),
                PokemonEntryUIModel("002", "Ivysaur", ""),
                PokemonEntryUIModel("003", "Venussaur", "")
            )
        )

        liveData.postValue(successState)

        listRobot {
            withSize(successState.value.size)
        }
    }

    private fun launchFragment() {
        launchFragmentInContainer<PokemonListFragment>(
            themeResId = R.style.AppTheme,
            factory = PokemonFragmentFactory(listViewModel, detailsViewModel)
        )
    }

    private inline fun listRobot(block: ListScreenRobot.() -> Unit) = ListScreenRobot().apply(block)
}