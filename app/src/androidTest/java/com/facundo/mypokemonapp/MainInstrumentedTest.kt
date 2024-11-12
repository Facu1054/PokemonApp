package com.facundo.mypokemonapp

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.rule.GrantPermissionRule
import com.facundo.mypokemonapp.server.MockWebServerRule
import com.facundo.mypokemonapp.server.fromJson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainInstrumentedTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    @get:Rule(order = 3)
    val androidComposeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {

        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("pokemon.json")
        )

        hiltRule.inject()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun click_a_pokemon_navigates_to_detail(): Unit = with(androidComposeRule) {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("pokemon_detailCharmander.json")
        )

        waitUntilAtLeastOneExists(hasParent(hasScrollToIndexAction()))

        onRoot().printToLog("HomeScreenState")
        onAllNodes(hasParent(hasScrollToIndexAction()), useUnmergedTree = true)[3]
            .assertExists()
            .performClick()


        androidComposeRule.waitUntil {
            onAllNodesWithTag("loadingIndicator").fetchSemanticsNodes().isEmpty()
        }
        onRoot().printToLog("DetailScreenTest")

        onAllNodesWithText("Charmander")[0].assertIsDisplayed()

    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun click_a_pokemon_navigates_to_detailBulbasaur(): Unit = with(androidComposeRule) {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("pokemon_detailBulbasaur.json")
        )

        waitUntilAtLeastOneExists(hasParent(hasScrollToIndexAction()))

        onRoot().printToLog("HomeScreenState")
        onAllNodes(hasParent(hasScrollToIndexAction()), useUnmergedTree = true)[0]
            .assertExists()
            .performClick()


        androidComposeRule.waitUntil {
            onAllNodesWithTag("loadingIndicator").fetchSemanticsNodes().isEmpty()
        }
        onRoot().printToLog("DetailScreenTest2")

        onAllNodesWithText("Bulbasaur")[0].assertIsDisplayed()

    }

}