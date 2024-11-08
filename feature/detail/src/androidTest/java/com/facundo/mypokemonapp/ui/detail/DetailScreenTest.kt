package com.facundo.mypokemonapp.ui.detail

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import com.facundo.mypokemonapp.ui.common.*
import com.facundo.mypokemonapp.ui.common.R
import com.facundo.mypokemonapp.ui.common.R.*
import com.facundo.unit.domain.pokemon.samplePokemon
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun foo(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                state = Result.Success(samplePokemon(2)),
                onBack = {},
                onFavoriteClicked = {},
                abilities = {}
            )

        }
        composeTestRule.onRoot().printToLog("DetailScreenTest")
    }

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                state = Result.Loading,
                onBack = {},
                onFavoriteClicked = {},
                abilities = {}
            )
        }

        onNodeWithTag(LOADING_INDICATOR_TAG).assertExists()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {

            DetailScreen(
                state = Result.Error(RuntimeException("An error occurred")),
                onBack = {},
                onFavoriteClicked = {},
                abilities = {}
            )
        }

        onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun whenSuccessState_movieIsShown(): Unit = with(composeTestRule) {
        setContent {

            DetailScreen(
                state = Result.Success(samplePokemon(2)),
                onBack = {},
                onFavoriteClicked = {},
                abilities = {}
            )

        }
        onAllNodesWithText("Title 2").onFirst().assertExists()

        //onNodeWithText("Title 2")
    }

    @Test
    fun whenFavoriteClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {

            DetailScreen(
                state = Result.Success(samplePokemon(2)),
                onBack = {},
                onFavoriteClicked = { clicked = true },
                abilities = {}
            )
        }

        onNodeWithContentDescription(getStringResource(string.favorite)).performClick()
        assertTrue(clicked)
    }

    @Test
    fun whenBackClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {

            DetailScreen(
                state = Result.Success(samplePokemon(2)),
                onBack = { clicked = true },
                onFavoriteClicked = {},
                abilities = {}
            )

        }
        //onAllNodesWithText("Title 2").onFirst()
        onNodeWithContentDescription(getStringResource(string.back)).performClick()
        assertTrue(clicked)
    }

    @Test
    fun whenSuccessState_showAbilities(): Unit = with(composeTestRule) {
        var abilities = samplePokemon(2).abilities.split(",")
        var isHiddenAbilities = samplePokemon(2).statusAbility.split(",")

        setContent {
            DetailScreen(
                state = Result.Success(samplePokemon(2)),
                onBack = {},
                onFavoriteClicked = {},
                abilities = {
                    PokeFormat("Ability 1: ",
                        abilities[0],
                        isHiddenAbilities[0].toBoolean()
                    )

                }
            )

        }
        onAllNodesWithText(abilities[0]).onFirst().assertExists()
    }

    @Test
    fun whenSuccessState_showHiddenAbility(): Unit = with(composeTestRule) {
        var abilities = samplePokemon(2).abilities.split(",")
        var isHiddenAbilities = samplePokemon(2).statusAbility.split(",")
        Log.i("hiddenAbTest", isHiddenAbilities[1].toString())

        setContent {
            DetailScreen(
                state = Result.Success(samplePokemon(2)),
                onBack = {},
                onFavoriteClicked = {},
                abilities = {
                    PokeFormat("Ability 1: ",
                        abilities[1],
                        isHiddenAbilities[1].toBoolean()
                    )

                }
            )

        }
        composeTestRule.onRoot().printToLog("DetailScreenTest")

        onAllNodesWithText("Hidden Ability").onFirst().assertExists()
    }

    @Test
    fun whenSuccessState_showNormalAbility(): Unit = with(composeTestRule) {
        var abilities = samplePokemon(2).abilities.split(",")
        var isHiddenAbilities = samplePokemon(2).statusAbility.split(",")

        setContent {
            DetailScreen(
                state = Result.Success(samplePokemon(2)),
                onBack = {},
                onFavoriteClicked = {},
                abilities = {
                    PokeFormat("Ability 1: ",
                        abilities[0],
                        isHiddenAbilities[0].toBoolean()
                    )

                }
            )

        }

        composeTestRule.onRoot().printToLog("DetailScreenTest")

        onAllNodesWithText(isHiddenAbilities[0]).onFirst().assertDoesNotExist()
    }

}

private fun getStringResource(@StringRes id: Int): String {
    val ctx = InstrumentationRegistry.getInstrumentation().targetContext
    return ctx.getString(id)
}