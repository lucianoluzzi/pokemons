package com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import com.lucianoluzzi.pokemons.PokemonFragmentFactory
import com.lucianoluzzi.pokemons.R
import com.lucianoluzzi.pokemons.details.domain.entity.Evolution
import com.lucianoluzzi.pokemons.details.domain.entity.PokemonDimension
import com.lucianoluzzi.pokemons.details.domain.entity.Type
import com.lucianoluzzi.pokemons.details.ui.PokemonDetailsUIModel
import com.lucianoluzzi.pokemons.details.ui.fragment.PokemonDetailsFragment
import com.lucianoluzzi.pokemons.details.ui.viewmodel.DetailsResponseState
import com.lucianoluzzi.pokemons.details.ui.viewmodel.PokemonDetailsViewModel
import com.lucianoluzzi.pokemons.pokemonlist.ui.fragment.robot.DetailsScreenRobot
import com.lucianoluzzi.pokemons.pokemonlist.ui.viewmodel.PokemonListViewModel
import com.lucianoluzzi.utils.coroutines.SingleLiveEvent
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test

class PokemonDetailsScreenTest {
    private val listViewModel = mock<PokemonListViewModel>()
    private val liveData = SingleLiveEvent<DetailsResponseState>()
    private val detailsViewModel = mock<PokemonDetailsViewModel> {
        on { pokemonDetails } doReturn liveData
    }

    @Before
    fun setUp() {
        launchFragment()
    }

    @Test
    fun checkProgressShownWhenStartup() {
        details {
            withProgress()
        }
    }

    @Test
    fun checkErrorShownWhenError() {
        val error = "Error!"
        liveData.postValue(DetailsResponseState.Error(error))

        details {
            withError(error)
        }
    }

    @Test
    fun checkTextValues() {
        val uiModel = getUIModel()
        liveData.postValue(
            DetailsResponseState.Success(
                uiModel
            )
        )

        details {
            withName(uiModel.name)
            withNumber(uiModel.number)
            withClassification(uiModel.classification)
            withHeight(uiModel.height)
            withWeight(uiModel.weight)
        }
    }

    @Test
    fun checkTypesBothTypesDisplayed() {
        val uiModel = getUIModel(
            types = listOf(
                Type("Fire"),
                Type("Ice")
            )
        )

        liveData.postValue(
            DetailsResponseState.Success(
                uiModel
            )
        )

        details {
            withFirstType()
            withSecondType()
        }
    }

    @Test
    fun checkOneTypeDisplayed() {
        val uiModel = getUIModel(
            types = listOf(
                Type("Fire")
            )
        )

        liveData.postValue(
            DetailsResponseState.Success(
                uiModel
            )
        )

        details {
            withFirstType()
            withoutSecondType()
        }
    }

    @Test
    fun checkWeakenessesListSize() {
        val weaknesses = listOf(
            Type("Fire"),
            Type("Fire"),
            Type("Fire")
        )
        val uiModel = getUIModel(
            weaknesses = weaknesses
        )

        liveData.postValue(
            DetailsResponseState.Success(
                uiModel
            )
        )

        details {
            withWeaknessesSize(weaknesses.size)
        }
    }

    @Test
    fun checkResistancesListSize() {
        val resistances = listOf(
            Type("Fire"),
            Type("Fire"),
            Type("Fire")
        )
        val uiModel = getUIModel(
            resistances = resistances
        )

        liveData.postValue(
            DetailsResponseState.Success(
                uiModel
            )
        )

        details {
            withResistancesSize(resistances.size)
        }
    }

    @Test
    fun checkEvolutionsListSize() {
        val evolutions = listOf(
            Evolution("", ""),
            Evolution("", ""),
            Evolution("", "")
        )
        val uiModel = getUIModel(
            evolutions = evolutions
        )

        liveData.postValue(
            DetailsResponseState.Success(
                uiModel
            )
        )

        details {
            withEvolutionsSize(evolutions.size)
        }
    }

    private fun getUIModel(
        evolutions: List<Evolution>? = null,
        types: List<Type> = listOf(Type("Electric")),
        weaknesses: List<Type> = listOf(),
        resistances: List<Type> = listOf()
    ): PokemonDetailsUIModel {
        return PokemonDetailsUIModel(
            number = "001",
            name = "Pikachu",
            classification = "Mouse pokémon",
            image = "",
            types = types,
            resistances = resistances,
            weaknesses = weaknesses,
            evolutions = evolutions.orEmpty(),
            weight = PokemonDimension("1.5kg", "2.5kg"),
            height = PokemonDimension("1.5m", "3m")
        )
    }

    private fun launchFragment() {
        launchFragmentInContainer<PokemonDetailsFragment>(
            themeResId = R.style.AppTheme,
            factory = PokemonFragmentFactory(listViewModel, detailsViewModel)
        )
    }

    private inline fun details(block: DetailsScreenRobot.() -> Unit) =
        DetailsScreenRobot().apply(block)
}