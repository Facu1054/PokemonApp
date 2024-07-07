package com.facundo.mypokemonapp.data

import com.facundo.mypokemonapp.data.model.Result
import com.facundo.mypokemonapp.data.model.pokemonInfo.Ability
import com.facundo.mypokemonapp.data.model.pokemonInfo.AbilityX
import com.facundo.mypokemonapp.data.model.pokemonInfo.Move
import com.facundo.mypokemonapp.data.model.pokemonInfo.MoveX
import com.facundo.mypokemonapp.data.model.pokemonInfo.PokemonInfoDTO
import com.facundo.mypokemonapp.data.model.pokemonInfo.Sprites
import com.facundo.mypokemonapp.data.model.pokemonInfo.Type
import com.facundo.mypokemonapp.data.model.pokemonInfo.TypeX
import com.facundo.mypokemonapp.data.datasource.remote.PokeApiClient
import com.facundo.mypokemonapp.data.datasource.remote.PokeService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test



class PokeRepositoryTest{
    val expectedPokemonInfo = PokemonInfoDTO(
        abilities = listOf(
            Ability(AbilityX("overgrow", "https://pokeapi.co/api/v2/ability/65/"), false, 1),
            Ability(AbilityX("chlorophyll", "https://pokeapi.co/api/v2/ability/34/"), true, 2)
        ),
        base_experience = 69,
        forms = mockk(),
        height = 7,
        held_items = mockk(),
        id = 1,
        is_default = true,
        location_area_encounters = "",
        moves = listOf(
            Move(MoveX("razor-wind", "https://pokeapi.co/api/v2/move/13/"), mockk()),
            Move(MoveX("swords-dance", "https://pokeapi.co/api/v2/move/14/"), mockk())
        ),
        name = "bulbasaur",
        order = 1,
        species = mockk(),
        sprites = Sprites(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        ),
        stats =
        listOf(
            mockk(),
            mockk(),
            mockk(),
            mockk(),
            mockk(),
            mockk()
        ),
        types = listOf(
            Type(1, TypeX("grass", "")),
            Type(2, TypeX("poison", ""))
        ),
        weight = 50
    )


        /*@Test
        fun listPokemon() {
            val expectedPokemonList = listOf(
                Result("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                Result(
                    "ivysaur",
                    "https://pokeapi.co/api/v2/pokemon/2/"
                )
            )
            val pokeService = mockk<PokeApiClient> {
                coEvery { listPokemon } returns flowOf(expectedPokemonList)
            }

            val repository = PokeRepository(pokeService)
            val result = runBlocking { repository.listPokemon.first() }

            assertEquals(expectedPokemonList, result)


        }*/

    @Test
    fun `when service is called then return list of pokemon`() {
        val expectedPokemonList = listOf(
            Result("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
            Result(
                "ivysaur",
                "https://pokeapi.co/api/v2/pokemon/2/"
            )
        )
        val apiClient = mockk<PokeApiClient> {
            coEvery { getAllPokemon().body()?.results } returns expectedPokemonList
        }

        val pokeService = PokeService(apiClient)
        val result = runBlocking { pokeService.getAllPokemon() }

        assertEquals(expectedPokemonList, result)
    }

    @Test
    fun `when service is called then return pokemon info`() {

        val apiClient = mockk<PokeApiClient> {
            coEvery { getPokemon(1).body() } returns expectedPokemonInfo
        }

        val pokeService = PokeService(apiClient)
        val result = runBlocking { pokeService.getPokemon(1) }

        assertEquals(expectedPokemonInfo, result)
    }




}