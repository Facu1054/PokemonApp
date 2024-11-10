package com.facundo.mypokemonapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.facundo.mypokemonapp.domain.pokemon.data.PokeRepository
import com.facundo.mypokemonapp.framework.pokemon.database.DbPokemon
import com.facundo.mypokemonapp.framework.pokemon.database.PokeDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class ExampleInstrumentedTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    /*@Inject
    lateinit var pokeRepository: PokeRepository*/

    @Inject
    lateinit var pokeDao: PokeDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }
    /*@Test
    fun test_it_works() {
        runBlocking {
            val movies = pokeRepository.pokemons.first()
            assertTrue(movies.isEmpty())
        }
    }*/

    @Test
    fun check_4_items_db() = runTest {
        pokeDao.save(buildDatabaseMovies(1, 2, 3, 4))
        val movies = pokeDao.fetchAllPokemon().first()
        assertEquals(4, movies.size)
    }

    @Test
    fun check_6_items_db() = runTest {
        pokeDao.save(buildDatabaseMovies(1, 2, 3, 4, 5, 6))
        assertEquals(6, pokeDao.fetchAllPokemon().first().size)
    }
}

fun buildDatabaseMovies(vararg id: Int) = id.map {
    DbPokemon(
        id = it,
        pokemonName = "Title $id",
        url = "",
        urlImage = "",
        type1 = "Fire",
        type2 = "",
        abilities = "Generic 1,Hidden Ability",
        statusAbility = "false,true",
        isFavorite = false
    )
}