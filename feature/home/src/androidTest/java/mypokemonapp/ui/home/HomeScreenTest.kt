package mypokemonapp.ui.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.facundo.mypokemonapp.ui.common.LOADING_INDICATOR_TAG
import com.facundo.mypokemonapp.ui.common.Result
import com.facundo.unit.domain.pokemon.samplePokemons
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.lang.RuntimeException

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun foo(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                homeViewModel = Result.Loading,
                onPokemonClick = {}
            )
        }
        composeTestRule.onRoot().printToLog("HomeScreenTest")
    }

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                homeViewModel = Result.Loading,
                onPokemonClick = {}
            )
        }

        onNodeWithTag(LOADING_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                homeViewModel = Result.Error(RuntimeException("An error ocurred")),
                onPokemonClick = {}
            )
        }

        onNodeWithText("An error ocurred").assertExists()
    }

    @Test
    fun whenSuccessState_showPokemons(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                homeViewModel = Result.Success(samplePokemons(1, 2, 3)),
                onPokemonClick = {}
            )
        }

        onNodeWithText("Title 2").assertExists()
    }

    @Test
    fun whenPokemonClicked_listenerIsCalled(): Unit = with(composeTestRule){
        var clickMovieId = -1
        var pokemon = samplePokemons(1,2,3,4)

        //Given
        setContent {

            HomeScreen(
                homeViewModel = Result.Success(pokemon),
                onPokemonClick = {clickMovieId = it.id}
            )

        }

        //When
        onNodeWithText("Title 2").performClick()

        Assert.assertEquals(2, clickMovieId)
    }
}